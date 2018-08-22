package com.people2000.user.web.utils.nodify.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.people2000.user.business.write.manage.MobileLoginWriteManage;

public class LoginFailureProcessor implements Runnable {

	private Logger log = LoggerFactory.getLogger(LoginFailureProcessor.class);

	private MobileLoginWriteManage loginWriteManage;
	private int code;
	private String username;
	private String ip;
	private String userAgent;

	public LoginFailureProcessor(MobileLoginWriteManage loginWriteManage,
			int code, String username, String ip, String userAgent) {
		this.loginWriteManage = loginWriteManage;
		this.code = code;
		this.username = username;
		this.ip = ip;
		this.userAgent = userAgent;
	}

	@Override
	public void run() {
		try {
			loginWriteManage.insertLoginExceptionLogWithTx(code, username, ip,
					userAgent);
		} catch (Exception e) {
			log.error("LoginFailureProcessor insert login exception failed.", e);
		}
	}

}
