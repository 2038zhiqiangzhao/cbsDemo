/**
 * 
 */
package com.people2000.user.business.utils;

import java.security.MessageDigest;

import com.people2000.user.business.utils.security.EncryptException;

/**
 * @author crc
 * @time 2015-5-13 下午4:53:23
 * @description
 * 
 * <pre>
 * 
 * </pre>
 * 
 */
public class SecurityUtils {

	public static String getMD5Str(String str) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] md5 = messageDigest.digest(str.getBytes("UTF-8"));
			return toHexString(md5);
		} catch (Exception e) {
			throw new EncryptException(e);
		}
	}

	private static String toHexString(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bs.length; i++) {
			int b = bs[i] & 0xFF;
			sb.append(b < 16 ? "0" + Integer.toHexString(b) : Integer
					.toHexString(b));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "" + Math.random() + System.currentTimeMillis();
		String newv = getMD5Str(str);
		System.err.printf("%s%n", newv);
	}
}
