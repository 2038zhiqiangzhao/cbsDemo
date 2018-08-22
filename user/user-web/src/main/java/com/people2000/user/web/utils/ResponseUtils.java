package com.people2000.user.web.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

	public static <T, X extends T, Y extends T> Map<String, T> map(X code,
			Y message) {
		Map<String, T> result = new HashMap<String, T>();
		result.put("code", code);
		result.put("message", message);
		return result;
	}

	private ResponseUtils() {
	}

}
