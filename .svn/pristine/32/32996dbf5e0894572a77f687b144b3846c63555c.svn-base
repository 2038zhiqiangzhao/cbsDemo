package com.people2000.user.business.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @time: 2016/9/19 14:38
 * @author: Wang Pengfei
 * @description:
 * @version:
 */
public enum UserActionLogEnum {
	register(1, "用户注册"), login(2, "登录"), changePassword(3, "修改密码"), completeUser(
			4, "完善信息");
	private int type;
	private String name;

	UserActionLogEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public static String getName(int type) {
		for (UserActionLogEnum c : UserActionLogEnum.values()) {
			if (c.getType() == type) {
				return c.name;
			}
		}
		return null;
	}

	public int getType() {
		return type;
	}
}
