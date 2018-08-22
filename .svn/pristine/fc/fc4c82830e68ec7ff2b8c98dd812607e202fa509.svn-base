package com.people2000.user.web.utils.nodify.handler;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.people2000.user.business.write.manage.MobileLoginWriteManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.model.po.ibatis.UserCookie;

public class LoginSuccessProcessor implements Runnable {

	private Logger log = LoggerFactory.getLogger(LoginSuccessProcessor.class);

	private MobileLoginWriteManage loginWriteManage;
	private UserCookieWriteManage userCookieWriteManage;

	private HashMap<String, Object> map;
	private UserCookie userCookie;

	public LoginSuccessProcessor(MobileLoginWriteManage loginWriteManage,
			UserCookieWriteManage userCookieWriteManage,
			HashMap<String, Object> map, UserCookie userCookie) {
		this.loginWriteManage = loginWriteManage;
		this.userCookieWriteManage = userCookieWriteManage;
		this.map = map;
		this.userCookie = userCookie;
	}

	@Override
	public void run() {
		try {
			Long userLoginId = loginWriteManage.insertLoginLogWithTx(map);
			userCookie.setUserLoginId(userLoginId);
			// TODO cookie存数据库的想法有待商榷，目前遵循原逻辑
			userCookieWriteManage.saveUserCookieWithTx(userCookie);
		} catch (Exception e) {
			log.error(
					"LoginSuccessHandler insert login log failed/save user cookie failed.",
					e);
		}
	}

}
