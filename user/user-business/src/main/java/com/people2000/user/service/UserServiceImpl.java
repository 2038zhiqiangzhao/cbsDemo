package com.people2000.user.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.interfaces.UserService;
import com.people2000.user.model.dto.UserCache;

@Service("userService")
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserCookieWriteManage userCookieWriteManage;

	@Override
	public UserCache getUserCache(String ut) {
		UserCache userCache = SsoClientUtil.getUser(ut);

		try {
			if (userCache == null) {
				userCache = userCookieWriteManage
						.getUserByLoginCookieWithTx(ut);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return userCache;
	}

}
