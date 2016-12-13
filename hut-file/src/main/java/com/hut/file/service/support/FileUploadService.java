package com.hut.file.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by Jared on 2016/12/11.
 */
public class FileUploadService implements com.hut.file.service.FileUploadService{

    @Autowired
    FileUploader uploader;
    @Autowired
    CloudFileMapper cloudFileMapper;

    Map<String,String> formats;

    @PostConstruct
    private void  init(){

        formats.put("jpg","image/jpeg");
        formats.put("png","image/png");
        formats.put("gif","image/gif");
        formats.put("jpeg","image/jpeg");

    }

    @Transactional
    public boolean upload(UploadForm form, byte[] data) {

        try {
            CloudFile  file =  new CloudFile();

            String  md5 = Crypto.createHash("md5").update(data).digestHex();

            String filename =  Utils.isEmpty(form.getFilename())?md5:form.getFilename();

            String contentType=null;

            if (form.getFile() != null) {
                contentType = form.getFile().getContentType();
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
            file.setAcl(form.getAcl());
            file.setCreatedAt(new Date());

            Product product  = (Product) Springs.getRequest().getAttribute(FileKeys.ACCESS_PRODUCT);

            file.setAppId(product.getAppId());
            file.setUserId(form.getUserId());

            saveFile(file, data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private  void  saveFile(CloudFile file,byte[] data){
        //根据 md5 和 文件大小 来 判断是否已经存在了这个文件。
        CloudFile maybe = this.cloudFileMapper.getFileByMd5(file.getMd5());

        if(maybe != null && maybe.getSize() == file.getSize()){
            //只要生成虚拟文件就可以了
            file.setFileIndex(maybe.getFileIndex());
        }
        else{
            //这里生成一下文件索引
            file.setFileIndex(uploader.createIndex(file, data));
        }

        this.cloudFileMapper.insertFile(file);
    }


    @SuppressWarnings("unused")
    private String getFormat(String format){
        int index =0;
        if((index = format.indexOf("."))>0){
            return format.substring(index+1,format.length());
        }
        return null;
    }

    @Transactional
    public boolean upload(UploadForm form) {
        if(Utils.isEmpty(form.getFilename())){
            form.setFilename(form.getFile().getOriginalFilename());
        }
        try {
            return  upload(form, form.getFile().getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("file serialize  fail");
        }
    }

    public CloudFile getCloudFileByPath(String path) {
        return this.cloudFileMapper.getFileByPath(path);
    }

    public byte[] getData(CloudFile file) {
        if (file != null) {
            return uploader.getFileData(file.getFileIndex());
        }
        return new byte[0];
    }



    public int sumByUserId(String userId) {
        return this.cloudFileMapper.sumByUserId(userId);
    }



    public int getTotalSizeByUserId(String userId) {
        return this.cloudFileMapper.getTotalSizeByUserId(userId);
    }

}
