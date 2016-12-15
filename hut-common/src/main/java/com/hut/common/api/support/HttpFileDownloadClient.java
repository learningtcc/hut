package com.hut.common.api.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hut.common.api.FileDownloadClient;
import com.hut.common.messages.Msg;
import com.hut.common.messages.MsgException;
import com.hut.common.utils.JacksonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.ResourceBundle;

/**
 * Created by Jared on 2016/12/15.
 */
public class HttpFileDownloadClient implements FileDownloadClient {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("META-INF/host");
    private String filehost = this.resourceBundle.getString("filehost");
    private CloseableHttpClient httpExecutor = HttpClients.createDefault();

    @Override
    public Msg<byte[]> download(String path) {
        try {
            String param = "?path="+path;
            String url = this.filehost+"/download/get"+param;
            HttpPost post = new HttpPost(url);
            CloseableHttpResponse execute = httpExecutor.execute(post);
            HttpEntity result = execute.getEntity();
            Msg<byte[]>  msg = JacksonUtils.toBean(result.getContent(),new TypeReference<Msg<byte[]>>() {});
            return msg;
        } catch (Exception e) {
            throw new MsgException(Msg.UNKNOW, "未知异常");
        }
    }
}
