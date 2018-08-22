package com.people2000.user.client.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.people2000.common.log.LogUtils;

/**
 * DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DesUtils {
	public DesUtils() {
	}

	// 测试
	public static void main(String args[]) {
		// 待加密内容
		String str = "af6379f7632444b998e03d2251cda6442";
		// 密码，长度要是8的倍数
		String password = "ertsffdr";

		Long l = System.currentTimeMillis();
		String encryptStr = DesUtils.encrypt(str, password);
		Long l2 = System.currentTimeMillis();
		System.out.println("加密后：" + encryptStr + " time:" + (l2 - l));

		String decryStr = null;
		try {
			decryStr = DesUtils
					.decrypt(
							"adb429fd01af6730c94a7056d51aec98dd903af470b22250b9ec0a94da33e4a4512042b83ff71485",
							"odianyun");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(DesUtils.class).error("解密出错", e);
		}
		Long l3 = System.currentTimeMillis();

		System.out.println("解密后:" + decryStr + " time:" + (l3 - l2));

	}

	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static String encrypt(String datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			System.out.println("xxxxxxxxxxxxxxxx加密的ut为： " + datasource
					+ " key为： " + password);
			// 正式执行加密操作
			byte[] ebyte = cipher.doFinal(datasource.getBytes());
			// String str=new String(Base64.encodeBase64(ebyte));
			int i = ebyte.length;
			char ac[] = new char[i * 2];
			int j = 0;
			for (int k = 0; k < i; k++) {
				byte byte0 = ebyte[k];
				ac[j++] = hexDigits[byte0 >>> 4 & 15];
				ac[j++] = hexDigits[byte0 & 15];
			}
			return new String(ac);

		} catch (Exception e) {
			LogUtils.getLogger(DesUtils.class).error("加密出错", e);
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static String decrypt(String src, String password) throws Exception {

		// byte[] res =Base64.decodeBase64(src);
		byte[] res = new byte[src.length() / 2];
		char[] chs = src.toCharArray();
		for (int i = 0, c = 0; i < chs.length; i += 2, c++) {
			res[c] = (byte) (Integer.parseInt(new String(chs, i, 2), 16));
		}
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);

		System.out.println("xxxxxxxxxxxxxxxx解密的ut为： " + src + " key为： "
				+ password);
		// 真正开始解密操作
		byte[] dbyte = cipher.doFinal(res);

		return new String(dbyte);
	}

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
