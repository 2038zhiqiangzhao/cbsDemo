package com.people2000.common.base.numeric;

import java.util.Random;
import java.util.UUID;

public class RandomCodeFactory {
	private static char[] ca = { '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static String generate(int n) {
		Random random = new Random();
		char[] cr = new char[n];
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(10);
			cr[i] = Integer.toString(x).charAt(0);
		}
		return new String(cr);
	}

	public static String generateMixed(int n) {
		Random random = new Random();
		char[] cr = new char[n];
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(32);
			cr[i] = ca[x];
		}
		return new String(cr);
	}

	public static String generateMixed2(int n) {
		Random random = new Random();
		char[] cr = new char[n];
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(9);
			cr[i] = ca[x];
		}
		return new String(cr);
	}

	public static String generateGUID() {
		String guid = UUID.randomUUID().toString();
		return guid.replaceAll("-", "").toLowerCase();
	}
}