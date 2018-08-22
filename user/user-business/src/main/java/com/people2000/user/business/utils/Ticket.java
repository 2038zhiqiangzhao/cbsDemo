package com.people2000.user.business.utils;

import java.io.Serializable;

import com.people2000.user.model.dto.UserCache;

/**
 * 
 * @deprecated 超时完全可以用缓存本身的缓存时间控制， 唯一的作用是过期时间在10分钟内登录续命，有需要的话在UserCache添加字段也能做到
 * @author crc
 * @time 2015-5-29 下午5:04:38
 * @description <pre>
 * 
 * </pre>
 *
 */
public class Ticket implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private UserCache userCache;

	private long time;

	public UserCache getUser() {
		return userCache;
	}

	public void setUser(UserCache userCache) {
		this.userCache = userCache;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
