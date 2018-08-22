package com.people2000.user.business.utils;

/**
 * Created by xiaole on 2016/2/17.
 */
public class CacheKeyUtils {

	// 添加一次
	public static void addOne(String mobile, Long companyId) {
		String key = getKey(mobile, companyId);
		final Object cache = CacheCommUtils.getCache(key);
		if (cache == null) {
			CacheCommUtils.addCache(key, 1, TimeUtils.getMinFromNowTOToday());
		} else {
			CacheCommUtils.putCache(key, (Integer) cache + 1,
					TimeUtils.getMinFromNowTOToday());
		}
	}

	// 拼接key
	public static String getKey(String mobile, Long companyId) {
		return "zgxc_" + mobile + "_" + companyId;
	}

	// 获取orginalIdkey
	public static String getOrginalIdkey(String orginalId) {
		return "zgxc_" + orginalId;
	}

	// 校验次数是否超过10次
	public static boolean checkTimes(String mobile, Long companyId) {
		String key = getKey(mobile, companyId);
		final Object cache = CacheCommUtils.getCache(key);
		if (cache == null) {
			return true;
		}
		if (10 <= (Integer) cache) {
			return false;
		}
		return true;
	}

	// 根据手机号码获取次数
	public static int getTimes(String mobile, Long companyId) {
		String key = getKey(mobile, companyId);
		final Object cache = CacheCommUtils.getCache(key);
		if (cache == null) {
			return 0;
		}
		return (Integer) cache;
	}

	// 拼接验证秘密的key
	public static String getPasswordKey(String value) {
		return "zgxc_password_" + value;
	}

	public static void main(String[] args) {
		Long as = null;
		System.out.println("as===" + as);
	}

}
