package com.people2000.user.business.utils.security;

/**
 * @version 1.0
 * @author wangpengfei
 *
 */
public interface PasswordEncrypt {
	/**
	 * 返回加密结果
	 * 
	 * @param word
	 * @param salt
	 * @return
	 */
	public String Encrypt(String word, String salt) throws EncryptException;
}
