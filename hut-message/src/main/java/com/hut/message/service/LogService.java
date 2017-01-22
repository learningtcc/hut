package com.hut.message.service;


import com.hut.message.pojo.EmailAccount;
import com.hut.message.pojo.EmailForm;

/**
 * Created by BruceJ on 2016/6/6.
 */
public interface LogService {

	void emailLogging(EmailAccount emailAccount, EmailForm emailForm,
					  String status, String msg);
}
