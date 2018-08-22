package com.people2000.user.model.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UserExtEnum {
	openId("openId"), miphone("注册手机"), miname("会员姓名"), miaddtime("注册时间"), mivshopcode(
			"所属店铺编号"), micardno("会员卡号"), misex("性别"), mibirthday("生日"), mirealname(
			"真实姓名"), miemail("邮箱"), miprofession("职业"), mishopcode("店铺编号"), minodename(
			"所属节点名称"), minodecode("所属节点编号"), openshop("是否开店"), miuserheader(
			"会员头像"), modifieddate("最后修改时间"), lastlogintime("最后登录时间"), miaddr(
			"地址"), miweixin("微信"), miinter(""), mistoreno(""), qq("qq"), micity(
			"城市"), miprovince("省"), mitown("镇"), miarea("地区"), mistorename(
			"店铺名"), mivshopname("微信店铺名"), mishopid("店铺id"),

	newuser("是否是新用户", "^(1|2)$")

	;
	private String showName;
	private String regExp;

	private UserExtEnum(String showName) {
		this.showName = showName;
	}

	private UserExtEnum(String showName, String regExp) {
		this.showName = showName;
		this.regExp = regExp;
	}

	public String getShowName() {
		return showName;
	}

	public String getRegExp() {
		return regExp;
	}

	/**
	 * 获取所有的key
	 * 
	 * @param key
	 * @param matcherStr
	 *            匹配字符串
	 * @return
	 */
	public static boolean getRegExp(String key, String matcherStr) {
		boolean flag = true;
		for (UserExtEnum thisObj : UserExtEnum.values()) {
			if (key.equals(thisObj.name())) {
				String patternStr = thisObj.getRegExp();
				if (patternStr != null && !patternStr.equals("")) {
					Pattern pattern = Pattern.compile(patternStr);
					Matcher matcher = pattern.matcher(matcherStr);
					flag = matcher.matches();
					if (!flag)
						break;
				}
			}
		}
		return flag;
	}

	/**
	 * key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean keyExists(String key) {
		boolean flag = false;
		for (UserExtEnum thisObj : UserExtEnum.values()) {
			if (key.equals(thisObj.name())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 获取枚举的中文描述
	 * 
	 * @param key
	 * @return
	 */
	public static String getShowName(String key) {
		String showName = "";
		for (UserExtEnum thisObj : UserExtEnum.values()) {
			if (key.equals(thisObj.name())) {
				showName = thisObj.showName;
			}
		}
		return showName;
	}
}