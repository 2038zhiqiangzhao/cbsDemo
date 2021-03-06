package com.people2000.common.session;

import java.util.HashMap;
import java.util.Map;

public class SystemContext {

	private static String USER_ID = "userId_";

	private static String USER_NAME = "userName_";

	private static String LOCALE = "locale_";

	private static transient ThreadLocal<Map<String, String>> contextMap = new ThreadLocal();

	private static Integer MAX_CAPACITY = Integer.valueOf(20);

	private static Integer MAX_SIZE = Integer.valueOf(200);

	public static Map<String, String> getContextMap() {
		return (Map) contextMap.get();
	}

	public static void setContextMap(Map<String, String> map) {
		contextMap.set(map);
	}

	public static String get(String key) {
		Map contextMap = getContextMap();
		if (contextMap == null) {
			return null;
		}

		return (String) contextMap.get(key);
	}

	public static String put(String key, String value) {
		if ((key == null) || (value == null)) {
			throw new RuntimeException("key:" + key + " or value:" + value
					+ " is null,i can't put it into the context map");
		}
		if (key.length() > MAX_SIZE.intValue()) {
			throw new RuntimeException("key is more than " + MAX_SIZE
					+ ", i can't put it into the context map");
		}
		if (value.length() > MAX_SIZE.intValue()) {
			throw new RuntimeException("value is more than " + MAX_SIZE
					+ ", i can't put it into the context map");
		}
		Map contextMap = getContextMap();
		if (contextMap == null) {
			contextMap = new HashMap();
			setContextMap(contextMap);
		}
		if (contextMap.size() > MAX_CAPACITY.intValue()) {
			throw new RuntimeException(
					"the context map is full, can't put anything");
		}
		return (String) contextMap.put(key, value);
	}

	public static String getLocale() {
		return get(LOCALE);
	}

	public static void setLocale(String locale) {
		put(LOCALE, locale);
	}

	public static void setUserId(Long userId) {
		if (userId != null)
			put(USER_ID, userId + "");
	}

	public static Long getUserId() {
		String userId = get(USER_ID);
		if ((userId == null) || ("null".equalsIgnoreCase(userId.trim()))) {
			return null;
		}
		return Long.valueOf(userId);
	}

	public static void setUserName(String userName) {
		if (userName != null)
			if (userName.length() > MAX_SIZE.intValue())
				put(USER_NAME, userName.substring(0, MAX_SIZE.intValue()));
			else
				put(USER_NAME, userName);
	}

	public static String getUserName() {
		return get(USER_NAME);
	}

	public static void clean() {
		contextMap.remove();
	}
}