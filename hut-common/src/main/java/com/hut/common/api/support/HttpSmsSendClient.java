package com.hut.common.api.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hut.common.api.SmsSendClient;
import com.hut.common.messages.Msg;
import com.hut.common.utils.JacksonUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URLEncoder;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Jared on 2016/12/15.
 */
public class HttpSmsSendClient implements SmsSendClient {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("META-INF/host");
    private String messagehost = this.resourceBundle.getString("messagehost");
    private CloseableHttpClient httpExecutor = HttpClients.createDefault();


    /**
     * 发送短信——助通科技
     */
	/*@Override
	public Msg sendSms(String mobile, String content) {


		CloseableHttpResponse response;

		try {

			String  qureystring  =  "?mobile="+ URLEncoder.encode(mobile, "UTF-8") + "&content="+ URLEncoder.encode(content, "UTF-8");

			HttpPost post = new HttpPost(this.messagehost + "/v1/sms/send"+qureystring);

			post.addHeader("X-WDCF-APPID", productId);
			post.addHeader("X-WDCF-Scret", secret);


		  StringEntity  stringBody  = new StringEntity(content, "UTF-8");


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

	}*/


    /**
     * 查询余额——助通科技
     */
	/*public Integer getBalance(String accountName) {
		Msg<Integer> result = this.doGet(messagehost+"/v1/sms/get/"+accountName, new TypeReference<Msg<Integer>>() {});
		if (result.getCode()==0) {
			return result.getResult();
		}
		throw new MsgException(result.getCode(),result.getMsg());
	}*/

    @Override
    public Msg sendSms(String mobile, String content) {

        try {
            String  qureystring  =  "?mobile="+ URLEncoder.encode(mobile, "UTF-8") + "&content="+ URLEncoder.encode(content, "UTF-8");
            HttpPost post = new HttpPost(this.messagehost + "/v1/huaxSms/send"+qureystring);
            CloseableHttpResponse response = this.httpExecutor.execute(post);
            return JacksonUtils.toBean(response.getEntity().getContent(), new TypeReference<Msg>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Msg(Msg.UNKNOW, "发送失败");
    }

    @Override
    public String getBalance(String accountName) {

        try {
            String  qureystring  =  "?accountName="+accountName;
            HttpPost post = new HttpPost(this.messagehost + "/v1/huaxSms/get"+qureystring);
            CloseableHttpResponse response = this.httpExecutor.execute(post);
            Map map =  JacksonUtils.toBean(response.getEntity().getContent(), Map.class);
            //resultMap  ==  {returnstatus=Success, message=操作成功, payinfo=预付, overage=13, sendTotal=20}
            Map resultMap =  (Map) map.get("result");
            return (String) resultMap.get("overage");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
