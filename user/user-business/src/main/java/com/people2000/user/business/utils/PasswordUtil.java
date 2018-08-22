package com.people2000.user.business.utils;

import com.people2000.user.business.utils.security.EncryptException;
import com.people2000.user.business.utils.security.PasswordEncrypt;
import com.people2000.user.business.utils.security.PasswordEncryptFactory;
import com.people2000.user.business.utils.security.PasswordEncryptType;

/**
 * 密码加密工具类
 * 
 * @version 1.0
 * @author huayuefei
 * 
 * @version 1.1
 * @author xuchao
 * 
 * @version 1.2
 * @author wangpengfei
 */
public class PasswordUtil {

	/**
	 * 使用对应的加密方法和盐进行加密
	 * 
	 * @param word
	 *            密码"明文"
	 * @param salt
	 *            盐
	 * @param version
	 *            加密版本
	 * @return String 加密后密文
	 */

	public static String getSecretPassWord(String word, String salt, int version) {

		PasswordEncryptType passwordEncryptType = PasswordEncryptType
				.of(version);

		if (null == passwordEncryptType) {
			throw new EncryptException("不支持此版本的加密算法: " + version);
		}
		PasswordEncrypt passwordEncrypt = PasswordEncryptFactory
				.creator(passwordEncryptType);
		String password = "";
		password = passwordEncrypt.Encrypt(word, salt);
		return password;
	}

	/**
	 * 获取加密算法
	 * 
	 * @param version
	 *            加密版本
	 * @return
	 */
	public static String getEncry(int version) {

		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param passWord
	 * @return
	 */
	public static String getSecretPassWord(String passWord) {
		String secretPassWord = null;
		if (passWord == null || "".equals(passWord)) {
			return secretPassWord;
		} else {
			// 这里暂不启用 added by xuc 20160509
			/*
			 * if(passWord.length()!=32){
			 * passWord=SecurityUtils.getMD5Str(SecurityUtils.getMD5Str(passWord
			 * )); }
			 * secretPassWord=SecurityUtils.getMD5Str(passWord.substring(0,20)+
			 * secretKey); return secretPassWord;
			 */

			/**
			 * 暂不启用上面的修改,保持原来的对明文进行一次md5加密 added by xuc 20160509
			 */
			secretPassWord = SecurityUtils.getMD5Str(passWord);
			return secretPassWord;
		}
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(getSecretPassWord("123456","xdwerewdds",1)); }
	 */
}
