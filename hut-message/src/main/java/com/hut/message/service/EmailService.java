package com.hut.message.service;

import com.hut.message.pojos.EmailAccount;

/**
 * Created by Jared on 2016/12/15.
 */
public interface EmailService {

    /**
     * 发送邮件
    */
    boolean sendEmail(EmailAccount emailAccount, EmailForm emailForm);
}
