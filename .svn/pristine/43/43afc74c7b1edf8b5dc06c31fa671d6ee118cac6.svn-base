package com.people2000.user.business.utils.security;

/**
 * @version 1.0
 * @author wangpengfei
 *
 */
public class PasswordEncryptFactory {

	/**
	 * 根据算法类型返回算法.
	 * 
	 * @param version
	 * @return
	 */
	public static PasswordEncrypt creator(PasswordEncryptType version) {
		return creator(version, null);
	}

	/**
	 * 根据算法类型返回算法.
	 * 
	 * @param version
	 * @return
	 */
	public static PasswordEncrypt creator(PasswordEncryptType version,
			String key) {
		// FIXME 暂时没有实现其它算法.
		// PasswordEncrypt encrypt = null;
		switch (version) {
		case MD5:
			return new MD5PasswordEncrypt();
			// case DES:
			// encrypt = new DESPasswordEncrypt();
			// return encrypt;
			// case DES3:
			// encrypt = new DES3PasswordEncrypt();
			// return encrypt;
			// case MD5:
			// return new MD5PasswordEncrypt();
		default:
			return new MD5PasswordEncrypt();
		}
	}

}
