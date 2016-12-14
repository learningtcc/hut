package com.hut.file.controller;

import com.hut.common.messages.Msg;
import com.hut.file.pojos.FileUploadBean;
import com.hut.file.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Jared on 2016/12/11.
 */
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping
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
        String suffix =StringUtils.getFilenameExtension(filename);

        // \\images\\2016\\03\\28\\yyyyMMddHHmmssSSSSXXXX.jpg
        String tempPath = this.getPicPath()+suffix;

        // /images/2016/03/28/yyyyMMddHHmmssSSSSXXXX.jpg
        String urlPath = StringUtils.replace(tempPath, "\\", "/");

        fileUploadBean.setPath(urlPath);

        Map map = new HashMap<String,String>();
        boolean uploaded = fileUploadService.upload(fileUploadBean, data);

        if (uploaded) {
            String callbackpath = "/download" + fileUploadBean.getPath();
            map.put("path",callbackpath);
            return new Msg<Map>(Msg.FILEUPLOADSUCCESSF,"上传成功",map);
        }
        return new Msg(Msg.FILEUPLOADFAIl,"上传失败");
    }


    @GetMapping("/count")
    public Msg getCount(@RequestParam("userId")int userId){
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

    @GetMapping("/size")
    public Msg getSize(@RequestParam("userId")int userId){
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
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalTime nowtime = LocalTime.now();
        int hour = nowtime.getHour();
        int minute = nowtime.getMinute();
        int second = nowtime.getSecond();

        Random random = new Random();
        String picPath =
                  File.separator + "file"
                + File.separator + year
                + File.separator + month
                + File.separator + day
                + File.separator + hour + minute + second
                + random.nextInt(9999);

        return picPath;
    }

}
