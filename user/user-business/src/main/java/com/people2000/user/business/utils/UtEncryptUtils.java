package com.people2000.user.business.utils;

import java.util.List;

import com.people2000.common.log.LogUtils;
import com.people2000.user.model.po.ibatis.UEncryptInfo;

public class UtEncryptUtils {

	public static String encryptUt(String ut, Long companyId) {// 加密ut
		String encryptUt = ut;
		List<UEncryptInfo> listEncrypt = (List<UEncryptInfo>) CacheCommUtils
				.getCache("encryptInfo");
		String key = null;
		if (null != listEncrypt && null != companyId) {
			for (UEncryptInfo info : listEncrypt) {
				if (companyId.equals(info.getCompanyId())) {
					key = info.getEncryptKey();
					break;
				}
			}
		}
		if (null == key)
			key = "odianyun";// 没拿到key,默认odianyun
		if (null != companyId) {
			encryptUt = DesUtils.encrypt(ut + companyId, key);
		}

		return encryptUt;
	}

	public static String dencryptUt(String ut, Long companyId) {// 解密ut
		String dencryptUt = ut;
		List<UEncryptInfo> listEncrypt = (List<UEncryptInfo>) CacheCommUtils
				.getCache("encryptInfo");
		String key = null;
		if (null != listEncrypt && null != companyId) {
			for (UEncryptInfo info : listEncrypt) {
				if (companyId.equals(info.getCompanyId())) {
					key = info.getEncryptKey();
					break;
				}
			}
		}
		if (null == key)
			key = "odianyun";// 没拿到key,默认odianyun
		try {
			dencryptUt = DesUtils.decrypt(ut, key);
			if (StringUtils.isNotEmpty(dencryptUt) && dencryptUt.length() > 32) {
				if (null != companyId
						&& (dencryptUt.substring(32)).equals(companyId + "")) {// companyId不符合
					return dencryptUt.substring(0, 32);
				} else if (null == companyId) {// companyId为null,为了兼容,不校验companyId
					return dencryptUt.substring(0, 32);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(UtEncryptUtils.class).error("解密ut异常", e);
		}
		return dencryptUt;
	}
}
