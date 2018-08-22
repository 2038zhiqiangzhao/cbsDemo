package com.people2000.user.business.utils.uionlogin;

/**
 * 联合登录工具类
 * 
 * @author dailf
 * @company ODY
 * @date 2016/7/8
 * @description
 */
public class UnionLoginUtils {
	/**
	 * 获取例如a=b&c=d的值
	 * 
	 * @param str
	 * @param key
	 * @return
	 */
	public static String getUrlValue(String str, String key) {

		String preStr = str.substring(str.indexOf(key) + key.length() + 1,
				str.length());
		if (preStr.contains("&")) {
			return preStr.substring(0, preStr.indexOf("&"));
		} else {
			return preStr;
		}
	}
}
