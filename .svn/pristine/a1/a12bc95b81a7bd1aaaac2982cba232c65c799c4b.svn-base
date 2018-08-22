package com.people2000.user.business.utils;

import java.util.Iterator;
import java.util.List;

import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.UserCookie;

/**
 * Created by xiaole on 2015/11/12.
 */
public class UpdateCacheUtil {
	public static void updateCache(List<UserCookie> list, UserCache userCache) {
		Iterator<UserCookie> iterator = list.iterator();
		while (iterator.hasNext()) {
			UserCookie userCookie = iterator.next();
			SsoClientUtil.login(userCache, userCookie.getCookieValue());
		}
	}

}
