package com.hut.file.controller;

import com.hut.file.pojos.PersistentFile;
import com.hut.file.service.FileUploadService;
import com.hut.file.utils.Area;
import com.hut.file.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jared on 2016/12/11.
 */

@WebServlet("/fileDownload")
public class ImgServlet extends HttpServlet {


    private static final String MODEL_SMALL = "_small";
    private static final String MODEL_BIG = "_big";
    private static final String MODEL_THUMBNAIL = "_thumbnail";
    private static final String MODEL_TINY = "_tiny";
    private static final String MODEL_ORIGINAL = "_original";

	private UrlPathHelper urlPathHelper;

    @Autowired
    private FileUploadService fileUploadService;

    public ImgServlet() {
        this.urlPathHelper = new UrlPathHelper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = urlPathHelper.getLookupPathForRequest(req);

            PersistentFile cfile = this.fileUploadService.getPersistentFileByPath(url);

            if(cfile!=null){
                byte[] data = this.fileUploadService.getData(cfile);
 
                resp.setContentType(cfile.getContentType());

                String model = req.getParameter("model");
                String scale = req.getParameter("scale");

                if (!StringUtils.isEmpty(scale)) {

                    data = scaleImage(scale,data);
                }
                else if (!StringUtils.isEmpty(model)) {
                    data = modelImage(model,data);
                }
                //默认 等比例压缩原图
                else{
                    data = ImageUtils.autoNarrow(data);
                }

                resp.getOutputStream().write(data);
                return;
            }

         resp.setStatus(200);
    }

    private byte[] modelImage(String model, byte[] data) {

        if (MODEL_BIG.equals(model)) {
            int big_pic_width = 550;
            int big_pic_height = 412;
            return ImageUtils.sizeNarrow(data, big_pic_width, big_pic_height);
        }
        else if (MODEL_SMALL.equals(model)) {
            int small_pic_width = 320;
            int small_pic_height = 240;
            return ImageUtils.sizeNarrow(data, small_pic_width,small_pic_height);
        }
        else if (MODEL_THUMBNAIL.equals(model)) {
            int thumbnail_pic_width = 107;
            int thumbnail_pic_height = 107;
            return ImageUtils.sizeNarrow(data, thumbnail_pic_width, thumbnail_pic_height);
        }
        else if (MODEL_TINY.equals(model)) {
            int tiny_pic_width = 60;
            int tiny_pic_height = 60;
            return ImageUtils.sizeNarrow(data, tiny_pic_width, tiny_pic_height);
        }
        else if (MODEL_ORIGINAL.equals(model)) {
            return data;
        }
        return null;
    }

    private byte[] scaleImage(String scale, byte[] data) {
        return ImageUtils.sizeNarrow(data,new Area(scale));
    }


}
