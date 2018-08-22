package com.people2000.user.business.utils.security;

/**
 * 对称加密方法接口
 * 
 * @version 1.0
 * @author wangpengfei
 *
 */
public interface SymPasswordEncrypt extends PasswordEncrypt {
	/**
	 * 添加密钥
	 * 
	 * @param key
	 */
	public void setKey(String key);
}
