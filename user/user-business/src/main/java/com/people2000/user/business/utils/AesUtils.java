package com.people2000.user.business.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AesUtils {

	public static void main(String[] args) throws Exception {
		String content = "12558552554abcbadce";
		System.out.println("加密前：" + content);

		String key = "123456";
		System.out.println("加密密钥和解密密钥：" + key);

		Long l = System.currentTimeMillis();

		String encrypt = aesEncryptToBytes(content, key).toString();
		Long l2 = System.currentTimeMillis();

		System.out.println("加密后：" + encrypt + " time:" + (l2 - l));

		String decrypt = aesDecryptByBytes(
				"8c271f02d85f644fdd14089855c4ddcab9e86fc26d9fc4c1d4de239b3bd718a4",
				key);
		Long l3 = System.currentTimeMillis();

		System.out.println("解密后：" + decrypt + " time:" + (l3 - l2));
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static String aesEncryptToBytes(String content, String encryptKey)
			throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(encryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey()
				.getEncoded(), "AES"));
		byte[] ebyte = cipher.doFinal(content.getBytes());

		// String str=new String(Base64.encodeBase64(ebyte));
		// return str;

		int i = ebyte.length;
		char ac[] = new char[i * 2];
		int j = 0;
		for (int k = 0; k < i; k++) {
			byte byte0 = ebyte[k];
			ac[j++] = hexDigits[byte0 >>> 4 & 15];
			ac[j++] = hexDigits[byte0 & 15];
		}

		return new String(ac);
	}

	/**
	 * AES解密
	 * 
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(String encrypt, String decryptKey)
			throws Exception {

		byte[] res = new byte[encrypt.length() / 2];
		char[] chs = encrypt.toCharArray();
		for (int i = 0, c = 0; i < chs.length; i += 2, c++) {
			res[c] = (byte) (Integer.parseInt(new String(chs, i, 2), 16));
		}

		// byte[] res =Base64.decodeBase64(encrypt);

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(decryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey()
				.getEncoded(), "AES"));

		byte[] dbyte = cipher.doFinal(res);

		return new String(dbyte);
	}

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

}
