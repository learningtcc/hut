package com.hut.message.service.support;

import com.hut.message.pojo.EmailAccount;
import com.hut.message.pojo.EmailForm;
import com.hut.message.service.EmailSender;
import com.hut.message.service.EmailService;
import org.springframework.stereotype.Service;

/**
 * Created by Jared on 2016/12/15.
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * 发送邮件
     */
    public boolean sendEmail(EmailAccount emailAccount, EmailForm emailForm) {
        EmailSender es = new EmailSender(emailAccount, logService);
        boolean endSuccessful = es.send(emailForm);
        return endSuccessful;
    }
}
