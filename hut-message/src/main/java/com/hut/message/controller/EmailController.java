package com.hut.message.controller;

import com.hut.common.messages.Msg;
import com.hut.message.pojos.Account;
import com.hut.message.pojos.EmailAccount;
import com.hut.message.pojos.EmailForm;
import com.hut.message.service.EmailService;
import com.hut.message.utils.FreeMarkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Jared on 2016/12/15.
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    /**
     * 发送 普通	邮件
     */
    @PostMapping(value="ordinary")
    @ResponseBody
    public Msg sendEmail(@RequestParam("subject")String subject,
                         @RequestParam("address")String[] address,
                         @RequestBody String content,
                         HttpServletRequest request){

        List<Account> accounts = this.accountMapper.queryListByAccuntType(AccountType.EMAILACCOUNT);
        Account account = accounts.get(0);
        String accountName = null;
        String host = null;
        String password = null;
        String productid = null;
        if (account!=null) {
            host = account.getHost();
            accountName = account.getAccountName();
            password = account.getAccountPassword();
            productid = account.getHost();
        }

        EmailAccount emailAccount = new EmailAccount();
        //163邮箱SMTP
        emailAccount.setHost(host);
        emailAccount.setPort(25);
        emailAccount.setAddress(accountName);
        emailAccount.setPassword(password);

        EmailForm emailForm = new EmailForm();
        emailForm.setAddress(Arrays.asList(address));
        emailForm.setContent(content);
        emailForm.setDisplayName(product.getAppName());
        emailForm.setHtml(true);
        emailForm.setSubject(subject);
        emailForm.setExtraLog("extralog:sha wan yi ne ");

        boolean sendSuccessful = this.emailService.sendEmail(emailAccount, emailForm);
        if (sendSuccessful) {
            return new Msg(Msg.OK,"发送成功");
        }
        return new Msg(Msg.UNKNOW, "发送失败");
    }


    /**
     * 发送 模板	邮件
     */
    @RequestMapping(value="template",method=RequestMethod.POST)
    @ResponseBody
    public Msg sendTemplateEmail(@RequestParam("subject")String subject,
                                 @RequestParam("address")String[] address,
                                 @RequestParam("varName")String varName,
                                 @RequestBody Map<?,?> params,
                                 HttpServletRequest request){

        List<Account> accounts = this.accountMapper.queryListByAccuntType(AccountType.EMAILACCOUNT);
        Account ac = accounts.get(0);
        String accountName = null;
        String host = null;
        String password = null;
        if (ac!=null) {
            host = ac.getHost();
            accountName = ac.getAccountName();
            password = ac.getAccountPassword();
        }
        //163邮箱SMTP
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setHost(host);
        emailAccount.setPort(25);
        emailAccount.setAddress(accountName);
        emailAccount.setPassword(password);

        //获取模板类
        Template template = this.templateService.queryTemplateByVarName(varName);
        //获取模板内容
        String contentTemplate = template.getContent();
        //获取模板生成freemark
        String content  =  FreeMarkerUtils.parseString(contentTemplate, params);


        EmailForm emailForm = new EmailForm();
        emailForm.setAddress(Arrays.asList(address));
        emailForm.setContent(content);
        Product product = (Product) request.getAttribute("_product");
        emailForm.setDisplayName(product.getAppName());
        emailForm.setHtml(true);
        emailForm.setSubject(subject);
        emailForm.setExtraLog("extralog:sha wan yi ne ");

        boolean sendSuccessful = this.emailService.sendEmail(emailAccount, emailForm);
        if (sendSuccessful) {
            return new Msg(Msg.OK,"发送成功");
        }
        return new Msg(Msg.UNKNOW, "发送失败");
    }
}
