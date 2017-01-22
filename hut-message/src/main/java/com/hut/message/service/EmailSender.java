package com.hut.message.service;

import com.google.common.base.Throwables;
import com.hut.common.utils.Utils;
import com.hut.message.pojo.EmailAccount;
import com.hut.message.pojo.EmailForm;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jared
 * @description 用户发送邮件的类, 根据LogService类来支持邮件的持久化问题,若不传递该类则不持久化
 */
public class EmailSender {
	
	private EmailAccount emailAccount;
	private LogService logService;

	public EmailSender(EmailAccount emailAccount, LogService logService) {
		super();
		this.emailAccount = emailAccount;
		this.logService = logService;
	}
	public boolean send(EmailForm emailForm){
		List<String> invalidAddress = new ArrayList<String>();
		String status=null;
		String msg ="";
		boolean  flag = true;
		try {
			JavaMailSenderImpl jmsl = createSpringJavaMailSenderSupport();

			MimeMessageHelper messageHelper = new MimeMessageHelper(
					jmsl.createMimeMessage(), false, "utf-8");
			messageHelper.setFrom(jmsl.getUsername(),
					(StringUtils.isEmpty(emailForm.getDisplayName())
							?emailAccount.getMailName():emailForm.getDisplayName()
					)
					+ "<" + jmsl.getUsername() + ">");
			messageHelper.setSubject(emailForm.getSubject());
			messageHelper.setText(emailForm.getContent(), emailForm.isHtml());
			for (String address : emailForm.getAddress()) {
				if(Utils.isEmailAddress(address)){
					messageHelper.addTo(address);
				}
				else{
					invalidAddress.add(address);
				}
			}
			jmsl.send(messageHelper.getMimeMessage());
			status="ok";
			if(invalidAddress.size()>0){
				msg="invalid:"+invalidAddress.toString();
			}
		} catch (Exception e) {
			flag =false;
			status="error";
			msg = Throwables.getStackTraceAsString(e);
		}
		if(logService!=null){
			logService.emailLogging(emailAccount, emailForm, status, msg);
		}
		return flag;
	}
	
	private JavaMailSenderImpl createSpringJavaMailSenderSupport() {
		JavaMailSenderImpl jmsl = new JavaMailSenderImpl();
		jmsl.getJavaMailProperties().put("mail.smtp.auth", "true");

		jmsl.setUsername(emailAccount.getAddress());
		jmsl.setPassword(emailAccount.getPassword());
		jmsl.setHost(emailAccount.getHost());
		jmsl.setPort(emailAccount.getPort());
		return jmsl;
	}

}
