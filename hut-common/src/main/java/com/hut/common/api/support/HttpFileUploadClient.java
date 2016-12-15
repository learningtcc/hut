package com.hut.common.api.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hut.common.api.FileUploadClient;
import com.hut.common.messages.Msg;
import com.hut.common.messages.MsgException;
import com.hut.common.utils.JacksonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Jared on 2016/12/15.
 */
public class HttpFileUploadClient implements FileUploadClient {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("META-INF/host");
    private String filehost = this.resourceBundle.getString("filehost");
    private CloseableHttpClient httpExecutor = HttpClients.createDefault();

    @Override
    public Msg<Map> upload(String filename, String userId, byte[] data) {

        String param = null;
        try {
            param = "?userId="+userId+"&filename="+ URLEncoder.encode(filename,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String uri = this.filehost+"/v1/upload/put"+param;
        HttpPost post = new HttpPost(uri);
        post.setEntity(new ByteArrayEntity(data));

        try {
            CloseableHttpResponse response = this.httpExecutor.execute(post);
            HttpEntity  result  = response.getEntity();
            Msg<Map> msg = JacksonUtils.toBean(result.getContent(),new TypeReference<Msg<Map>>() {});
            return msg;

        } catch (IOException e) {
            throw new MsgException(Msg.UNKNOW, "");
        }
    }

    @Override
    public Map totalCount(String userId) {
        String param = "?userId="+userId;
        HttpGet hg = new HttpGet(this.filehost+"/upload/count"+param);
        try {
            CloseableHttpResponse execute = httpExecutor.execute(hg);

            HttpEntity entity = execute.getEntity();

            Msg<Map> msg = JacksonUtils.toBean(entity.getContent(),new TypeReference<Msg<Map>>() {});
            if (msg.getCode()==0) {
                return msg.getEntity();
            }

            throw new MsgException(msg.getCode(),msg.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new MsgException(Msg.UNKNOW, "");
    }


    @Override
    public Map totalSize(String userId) {
        String param = "?userId="+userId;
        HttpGet hg = new HttpGet(this.filehost+"/upload/size"+param);
        try {
            CloseableHttpResponse execute = httpExecutor.execute(hg);
            HttpEntity entity = execute.getEntity();
            Msg<Map> msg = JacksonUtils.toBean(entity.getContent(),new TypeReference<Msg<Map>>() {});

            if (msg.getCode()==0) {
                return msg.getEntity();
            }
            throw new MsgException(msg.getCode(),msg.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new MsgException(Msg.UNKNOW, "");
    }
}
