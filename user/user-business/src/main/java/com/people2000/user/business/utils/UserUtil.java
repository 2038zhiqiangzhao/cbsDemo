package com.people2000.user.business.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2015/4/10.
 */
public class UserUtil {
	/**
	 * TODO:获取当前账号的主账号id 系统中所有userId的地方调用这个方法
	 *
	 * @return
	 */
	public static Long getCurrentMainAccountUserId() {
		// return 1L;
		return SessionContainer.getUserId();
	}

	/**
	 * TODO:获取当前操作员的账号id 系统中所有 craeteUserId updateUserId调用这个方法
	 *
	 * @return
	 */
	public static Long getCurrentOperateAccountUserId() {
		// return 1L;
		return SessionContainer.getOperateId();
	}

	public static void checkUserid(long post_data_userId, long db_data_userId) {
		if ((post_data_userId == db_data_userId && post_data_userId == getCurrentMainAccountUserId()) == false) {
			throw new RuntimeException(" 所修改的数据不属于您！，请重新登录 ");
		}
	}

	public static List createList(Object... objs) {
		List list = new ArrayList();
		if (objs != null) {
			for (Object o : objs) {
				list.add(o);

			}
		}
		return list;
	}

	public static String getLimit(Integer currentPage, Integer itemsPerPage) {
		int start = (currentPage - 1) * itemsPerPage;
		if (start < 0) {
			start = 0;
		}
		return " limit " + start + "," + itemsPerPage;
	}

	/**
	 * 邮箱地址校验
	 *
	 * @param email
	 * @return
	 */
	public static boolean isEmailAddress(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[_|-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * 手机号验证
	 *
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static String generateTaskNo(String prefix, Long userId) {
		return prefix + userId
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	// 生成应用的UID
	public static String generateAppID(String uid) {
		String uuid = UUID.randomUUID().toString();

		return uid + uuid.substring(uuid.length() - 5)
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	// 生成应用的密匙
	public static String generateAppSecKey(String uid) {
		String uuid = UUID.randomUUID().toString();

		return uid + uuid.substring(uuid.length() - 10)
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// return uid + UUID.randomUUID().toString() + UUID.randomUUID();
	}

	public static String toString(Collection collection) {
		if (collection == null || collection.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Object ob : collection) {
			sb.append(ob + "").append(",");
		}
		return sb.toString();
	}

}
