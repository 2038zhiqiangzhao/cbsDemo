package com.people2000.user.business.utils;

import java.util.Random;

public class SaltUtils {

	public static String getRandomSalt(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789@#$%&*";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/*
	 * public static void main(String[] args){ String slat=getRandomSalt(10);
	 * System.out.println(slat); }
	 */
}