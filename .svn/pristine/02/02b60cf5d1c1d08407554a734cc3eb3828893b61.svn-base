package com.people2000.user.client.utils;

import java.util.Set;

import com.people2000.user.model.dto.UserCache;

/**
 * @author crc
 * @time 2015-5-29 下午5:06:46
 * @description <pre>
 * <p/>
 * </pre>
 */
public class SessionContainer {

	private static final ThreadLocal<UserCache> sessionThreadLocal = new ThreadLocal<UserCache>();

	public static UserCache getSession() {
		return sessionThreadLocal.get();
	}

	/**
	 * @return
	 * @description <pre>
	 * 如果是子账户，返回主账户id，如果是主账户，返回自己的id
	 * </pre>
	 */
	public static Long getUserId() {
		
		Long userId = sessionThreadLocal.get().getParentId();
		if (userId.longValue() == 0) {
			userId = sessionThreadLocal.get().getId();
		}
		return userId;
	}

	/**
	 * @return
	 * @description <pre>
	 * 返回当前登陆人权限code
	 * </pre>
	 */
	public static String getfunctionCodes() {
		return sessionThreadLocal.get().getFunctionCodes();
	}

	public static String getMobile() {
		return sessionThreadLocal.get().getMobile();
	}

	/**
	 * @return
	 * @description <pre>
	 * 返回当前登陆人权限路径
	 * </pre>
	 */
	public static Set<String> getFunctionPahts() {
		return sessionThreadLocal.get().getFunctionPahts();
	}

	public static void setSession(UserCache c) {
		sessionThreadLocal.set(c);
	}

	public static void clear() {
		sessionThreadLocal.set(null);
	}

	/**
	 * @return
	 * @description <pre>
	 * n
	 * 当前登陆人的id
	 * </pre>
	 */
	public static Long getOperateId() {
		return sessionThreadLocal.get().getId();
	}

	/**
	 * @return
	 * @description <pre>
	 * 当前登陆人所属公司
	 * </pre>
	 */
	public static Long getCompanyId() {
		return sessionThreadLocal.get().getCompanyId();
	}

	/**
	 * 原始id
	 *
	 * @return
	 */
	public static String getOriginalId() {
		return sessionThreadLocal.get().getOriginalId();
	}
}
