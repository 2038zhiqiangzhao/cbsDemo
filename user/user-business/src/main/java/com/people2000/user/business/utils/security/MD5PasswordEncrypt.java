package com.people2000.user.business.utils.security;

import com.people2000.common.encrypt.MD5Support;

/**
 * md5 加密方式
 * 
 * @version 1.0
 * @author wangpengfei
 *
 */
public class MD5PasswordEncrypt implements PasswordEncrypt {

	@Override
	public String Encrypt(String word, String salt) {
		return MD5Support.MD5(word + salt);
	}

}
