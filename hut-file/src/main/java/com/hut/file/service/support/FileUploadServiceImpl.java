package com.hut.file.service.support;

import com.hut.file.dao.FileUploadMapper;
import com.hut.file.pojos.FileUploadBean;
import com.hut.file.pojos.PersistentFile;
import com.hut.file.service.FileUploadService;
import com.hut.file.service.FileUploader;
import com.hut.file.utils.Crypto;
import com.hut.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jared on 2016/12/11.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    FileUploader uploader;

    @Autowired
    FileUploadMapper fileUploadMapper;

    Map<String,String> formats;

    @PostConstruct
    private void  init(){
        formats = new HashMap<>();
        formats.put("jpg","image/jpeg");
        formats.put("png","image/png");
        formats.put("gif","image/gif");
        formats.put("jpeg","image/jpeg");
    }

    @Transactional
    public boolean upload(FileUploadBean form, byte[] data) {

        try {
            PersistentFile file =  new PersistentFile();

            String  md5 = Crypto.createHash("md5").update(data).digestHex();

            String filename =  Utils.isEmpty(form.getFilename())?md5:form.getFilename();

            String contentType=null;

            if (form != null) {
                contentType = form.getContentType();
            }
            if (Utils.isEmpty(contentType))  {
                contentType = form.getContentType();
            }
            if (Utils.isEmpty(contentType))  {
                //取到文件的后缀名， 去map 中匹配一个
                int lastIndexOf = form.getFilename().lastIndexOf(".");
                contentType = formats.get(form.getFilename().substring(lastIndexOf+1));
            }


            contentType = Utils.isEmpty(contentType)? MediaType.APPLICATION_OCTET_STREAM_VALUE:contentType;

            file.setPath(form.getPath());
            file.setMd5(md5);
            file.setContentType(contentType);
            file.setFilename(filename);
            file.setSize(data.length);
            file.setCreatedAt(new Date());
            file.setUserId(form.getUserId());

            saveFile(file, data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private  void  saveFile(PersistentFile file,byte[] data){
        //根据 md5 和 文件大小 来 判断是否已经存在了这个文件。
        PersistentFile maybe = this.fileUploadMapper.getFileByMd5(file.getMd5());

        if(maybe != null && maybe.getSize() == file.getSize()){
            //只要生成虚拟文件就可以了
            file.setFileIndex(maybe.getFileIndex());
        }
        else{
            //这里生成一下文件索引
            file.setFileIndex(uploader.createIndex(file, data));
        }

        this.fileUploadMapper.insertFile(file);
    }


    @SuppressWarnings("unused")
    private String getFormat(String format){
        int index =0;
        if((index = format.indexOf("."))>0){
            return format.substring(index+1,format.length());
        }
        return null;
    }

    public int getSumByUserId(int userId) {
        return this.fileUploadMapper.sumByUserId(userId);
    }

    public int getTotalSizeByUserId(int userId) {
        return this.fileUploadMapper.getTotalSizeByUserId(userId);
    }


    public PersistentFile getPersistentFileByPath(String path) {
        return this.fileUploadMapper.getFileByPath(path);
    }

    public byte[] getData(PersistentFile file) {
        if (file != null) {
            return uploader.getFileData(file.getFileIndex());
        }
        return new byte[0];
    }

}
