package com.hut.common.api.support;

import com.hut.common.api.EmailSendClient;
import com.hut.common.messages.Msg;
import com.hut.common.utils.JacksonUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Jared on 2016/12/15.
 */
public class HttpEmailSendClient implements EmailSendClient {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("META-INF/host");
    private String messagehost = this.resourceBundle.getString("messagehost");
    private CloseableHttpClient httpExecutor = HttpClients.createDefault();

    /**
     * 发送普通邮件
     */
    @Override
    public Msg sendEmail(String subject, String[] address, String content) {

        StringBuilder sb = new StringBuilder();
        for (String str : address) {
            if (str != null && str != "") {
                sb.append("&address=" + str);
            }
        }
        CloseableHttpResponse response = null;
        try {
            String  qureystring  =  "?subject="+ URLEncoder.encode(subject, "UTF-8") + sb.toString();
            HttpPost post = new HttpPost(this.messagehost + "/v1/email/general"+qureystring);
            StringEntity stringBody  = new StringEntity(content, "UTF-8");
            post.setEntity(stringBody);
            response = this.httpExecutor.execute(post);

            if (response.getEntity() != null) {
                return new Msg(response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity(), "UTF-8"));
                //读取后自动关流
            }
            return new Msg(Msg.UNKNOW, "发送失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Msg(Msg.UNKNOW, "发送失败");
    }

    /**
     * 发送模板邮件
     */
    public Msg sendEmailByTemplate(String subject, String[] address,String varName,Map<?, ?> map){

        //封装收件人参数
        StringBuilder sb = new StringBuilder();
        for (String str : address) {
            if (str != null && str != "") {
                sb.append("&address=" + str);
            }
        }
        CloseableHttpResponse response = null;
        try {
            //封装请求参数
            //String  qureystring  =  "?subject="+ URLEncoder.encode(subject, "UTF-8") + sb.toString()+"&varName="+varName;
            String  qureystring  =  "?subject="+ subject + sb.toString()+"&varName="+varName;
            HttpPost post = new HttpPost(this.messagehost + "/v1/email/template"+qureystring);
            post.addHeader(HttpHeaders.CONTENT_TYPE,"application/json;charset=utf-8");
            StringEntity  stringBody  = new StringEntity(JacksonUtils.toJsonString(map), "UTF-8");
            post.setEntity(stringBody);
            response = this.httpExecutor.execute(post);
            if (response.getEntity() != null) {
                return new Msg(response.getStatusLine().getStatusCode(),
                        EntityUtils.toString(response.getEntity(), "UTF-8"));
                //读取后自动关流
            }
            return new Msg(Msg.UNKNOW, "发送失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Msg(Msg.UNKNOW, "发送失败");
    }
}
