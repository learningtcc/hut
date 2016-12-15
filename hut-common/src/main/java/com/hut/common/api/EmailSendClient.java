package com.hut.common.api;

import com.hut.common.messages.Msg;

import java.util.Map;

/**
 * Created by Jared on 2016/12/15.
 */
public interface EmailSendClient {

    Msg sendEmail(String subject, String[] address, String content);

    Msg sendEmailByTemplate(String subject, String[] address,String varName, Map<?, ?> map);
}
