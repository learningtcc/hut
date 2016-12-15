package com.hut.common.api;

import com.hut.common.messages.Msg;

/**
 * Created by Jared on 2016/12/15.
 */
public interface SmsSendClient {

    Msg sendSms(String mobile, String content);

    String getBalance(String accountName);
}
