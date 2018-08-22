package com.people2000.user.web.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;

public class CookieWrapper {

	private Map<String, Cookie> cookieMap = new ConcurrentHashMap<String, Cookie>();

	public CookieWrapper(Cookie[] cookies) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && cookie.getName() != null) {
					cookieMap.put(cookie.getName(), cookie);
				}
			}
		}
	}

	public String getCookieValue(String cookieName) {
		Cookie cookie = cookieMap.get(cookieName);
		return cookie == null ? null : cookie.getValue();
	}

}
