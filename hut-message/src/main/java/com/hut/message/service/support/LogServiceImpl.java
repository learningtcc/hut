package com.hut.message.service.support;

import com.wdcf.email.EmailAccount;
import com.wdcf.email.EmailForm;
import com.wdcf.entity.EmailDetail;
import com.wdcf.entity.Product;
import com.wdcf.mapper.TextTemplateMapper;
import com.wdcf.service.LogService;
import com.wdcf.utils.Springs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by BruceJ on 2016/6/6.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    TextTemplateMapper operatingLogMapper;

	@Override
	public void emailLogging(EmailAccount emailAccount, EmailForm emailForm, String status, String msg) {
		
		EmailDetail emailDetail = new EmailDetail();
		emailDetail.setCreatedAt(new Date());
		String[] array = (String[]) emailForm.getAddress().toArray();
		emailDetail.setAddress(Arrays.toString(array));
		emailDetail.setSubject(emailForm.getSubject());
		emailDetail.setContent(emailForm.getContent());
		emailDetail.setStatus(status);
		emailDetail.setErrorMsg(msg);
		emailDetail.setFrom(emailAccount.getAddress());

		this.operatingLogMapper.saveEmailDetail(emailDetail);
		this.operatingLogMapper.plusTotal(emailAccount);
	}
}
