package com.hut.file.controller;

import com.hut.common.messages.Msg;
import com.hut.file.pojos.FileUploadBean;
import com.hut.file.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jared on 2016/12/11.
 */
@RestController
@RequestMapping("/fileup")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Msg put(
            @RequestParam("userId")String userId,
            @RequestParam("filename")String filename,
            @RequestParam(value="contentType",defaultValue="")String contentType,
            @RequestBody byte[] data
    ){
        FileUploadBean fileUploadBean = new FileUploadBean();
        fileUploadBean.setUserId(userId);
        fileUploadBean.setFilename(filename);
        fileUploadBean.setContentType(contentType);

        // 拼接文件url
        int index =  filename.lastIndexOf(".");
        String suffix =index>0?filename.substring(index,filename.length())+"":"";

        // \\images\\2016\\03\\28\\yyyyMMddHHmmssSSSSXXXX.jpg
        String tempPath = this.getPicPath()+suffix;
        // /images/2016/03/28/yyyyMMddHHmmssSSSSXXXX.jpg
        String urlPath = StringUtils.replace(tempPath, "\\", "/");

        fileUploadBean.setPath(urlPath);

        Map map = new HashMap<String,String>();
        boolean uploaded = fileUploadService.upload(fileUploadBean, data);

        if (uploaded) {
            String callbackpath = "/img-lib" + fileUploadBean.getPath();
            map.put("path",callbackpath);
            return new Msg<Map>(Msg.FILEUPLOADSUCCESSF,"上传成功",map);
        }
        return new Msg(Msg.FILEUPLOADFAIl,"上传失败");
    }


    @GetMapping("count")
    public Msg getCount(@RequestParam("userId")String userId){
        try {
            Map map = new HashMap<String,Integer>();
            int sum = this.fileUploadService.getSumByUserId(userId);
            map.put("totalCount",sum);
            return new Msg<Map>(Msg.OK,"获取文件数量成功",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Msg(Msg.UNKNOW,"获取文件数量失败");
    }

    @GetMapping("size")
    public Msg getSize(@RequestParam("userId")String userId){
        try {
            Map map = new HashMap<String,Integer>();
            int size = this.fileUploadService.getTotalSizeByUserId(userId);
            map.put("totalSize",size);
            return new Msg<Map>(Msg.OK,"获取文件大小成功",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Msg(Msg.UNKNOW,"获取文件大小失败");
    }


    private String getPicPath() {

        LocalDate now = LocalDate.now();
        int year = now.getYear();

        // \\images\\2016\\03\\28\\yyyyMMddHHmmssSSSSXXXX.jpg
        String picPath = File.separator+"images"+File.separator+new DateTime(nowDate).toString("yyyy")
                + File.separator + new DateTime(nowDate).toString("MM") + File.separator
                + new DateTime(nowDate).toString("dd")+ File.separator+new DateTime(nowDate).toString("yyyyMMddHHmmssSSSS")
                + RandomUtils.nextInt(1000, 9999);

        return picPath;
    }

}
