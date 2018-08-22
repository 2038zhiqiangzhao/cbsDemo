package com.people2000.user.business.utils.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加密算法枚举, 后续从数据库里面读取
 * 
 * @version 1.0
 * @author wangpengfei
 *
 */
public enum PasswordEncryptType {

	MD5(1), DES(2), DES3(3);

	private int value;

	private PasswordEncryptType(int value) {
		this.value = value;
	}

	public static PasswordEncryptType of(int value) {
		return LazyLoad.TypeToInstanceMap.get(value);
	}

	private static class LazyLoad {
		public static final Map<Integer, PasswordEncryptType> TypeToInstanceMap = new ConcurrentHashMap<Integer, PasswordEncryptType>();
		static {
			for (PasswordEncryptType type : PasswordEncryptType.values()) {
				TypeToInstanceMap.put(type.value, type);
			}
		}
	}

}
