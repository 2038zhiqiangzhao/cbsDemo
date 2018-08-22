package com.people2000.user.business.utils;

import java.util.Date;

import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.model.po.ibatis.User;

public class CalcEncryInfoUtils {

	// 计算用户的加密算法版本跟盐值的信息
	public static EncryptEntity calc(User user) {
		EncryptEntity encryptEntity = new EncryptEntity();
		if (user != null) {
			// 前端算法盐值信息
			if (null == user.getfAlgorithmVersion()) {
				encryptEntity.setNewFVersion(1);
			} else {
				// 后面需要跟平台当前算法比较
				if (1 != 1) {
					encryptEntity.setNewFVersion(1);
				}
				encryptEntity.setOldFVersion(user.getfAlgorithmVersion());
			}

			if (StringUtils.isEmpty(user.getfSalt())) {
				encryptEntity.setNewFSalt(SaltUtils.getRandomSalt(10));
			} else {
				if (null == user.getfSaltUpdateTime()) {
					encryptEntity.setNewFSalt(SaltUtils.getRandomSalt(10));
					encryptEntity.setOldFSalt(user.getfSalt());

				} else {
					Date date = user.getfSaltUpdateTime();
					if ((((new Date()).getTime() - date.getTime()) / 86400000) >= 7) {// 这里默认7天
						encryptEntity.setOldFSalt(user.getfSalt());
						encryptEntity.setNewFSalt(SaltUtils.getRandomSalt(10));

					} else {
						encryptEntity.setOldFSalt(user.getfSalt());
					}
				}
			}

			// 后端算法盐值信息
			if (null == user.getbAlgorithmVersion()) {
				encryptEntity.setNewBVersion(1);
			} else {
				// 后面需要跟平台当前算法比较
				if (1 != 1) {
					encryptEntity.setNewBVersion(1);
				}
				encryptEntity.setOldBVersion(user.getbAlgorithmVersion());
			}

			if (StringUtils.isEmpty(user.getbSalt())) {
				encryptEntity.setNewBSalt(SaltUtils.getRandomSalt(10));
			} else {
				if (null == user.getbSaltUpdateTime()) {
					encryptEntity.setNewBSalt(SaltUtils.getRandomSalt(10));
					encryptEntity.setOldBSalt(user.getbSalt());
				} else {
					Date date = user.getbSaltUpdateTime();
					if ((((new Date()).getTime() - date.getTime()) / 86400000) >= 7) {// 这里默认7天
						encryptEntity.setOldBSalt(user.getbSalt());
						encryptEntity.setNewBSalt(SaltUtils.getRandomSalt(10));

					} else {
						encryptEntity.setOldBSalt(user.getbSalt());
					}
				}
			}
			// 返回user的id
			encryptEntity.setUserId(user.getId());

		}
		return encryptEntity;
	}
}
