package com.people2000.user.web.utils.nodify.handler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.PasswordUtil;
import com.people2000.user.business.utils.StringUtils;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.write.manage.PasswordEncryptInfoWriteManage;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.vo.MobileUserVO;

public class PasswordUpdateProcessor implements Runnable {

	private MobileUserVO user;
	private List<User> listUser;
	private EncryptEntity encryptEntity;
	private PasswordEncryptInfoWriteManage passwordEncryptInfoWriteManage;

	public PasswordUpdateProcessor(MobileUserVO user, List<User> listUser,
			EncryptEntity encryptEntity,
			PasswordEncryptInfoWriteManage passwordEncryptInfoWriteManage) {
		this.user = user;
		this.listUser = listUser;
		this.encryptEntity = encryptEntity;
		this.passwordEncryptInfoWriteManage = passwordEncryptInfoWriteManage;
	}

	@Override
	public void run() {
		try {
			Long userId = listUser.get(0).getId();

			// 前端传入了新的加密密码,登录成功之后置为当前密码
			if (StringUtils.isNotEmpty(user.getNewEncryptPassword())) {
				user.setPassword(user.getNewEncryptPassword());
			}

			if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())
					|| null != encryptEntity.getNewBVersion()
					|| StringUtils.isNotEmpty(user.getNewEncryptPassword())) {
				String salt = StringUtils.isEmpty(encryptEntity.getNewBSalt()) ? encryptEntity
						.getOldBSalt() : encryptEntity.getNewBSalt();
				Integer version = (null == encryptEntity.getNewBVersion()) ? encryptEntity
						.getOldBVersion() : encryptEntity.getNewBVersion();

				User updUser = new User();

				updUser.setId(userId);

				String password = PasswordUtil.getSecretPassWord(
						user.getPassword(), salt, version);
				Date now = new Date();
				if (StringUtils.isNotEmpty(encryptEntity.getNewBSalt())) {
					updUser.setbSalt(encryptEntity.getNewBSalt());
					updUser.setbSaltUpdateTime(now);
				}
				if (null != encryptEntity.getNewBVersion()) {
					updUser.setbAlgorithmVersion(encryptEntity.getNewBVersion());
				}

				if (StringUtils.isNotEmpty(user.getNewFSalt())) {
					updUser.setfSalt(user.getNewFSalt());
					updUser.setfSaltUpdateTime(now);
				}

				if (null != user.getNewFVersion()) {
					updUser.setfAlgorithmVersion(user.getNewFVersion());
				}

				updUser.setPassword(password);

				int cnt = passwordEncryptInfoWriteManage
						.updateEncryptInfoByUserWithTx(updUser, null);

				if (0 == cnt) {
					LogUtils.getLogger(this.getClass()).error("更新用户加密密码失败");
				}

			}
		} catch (SQLException e) {
			LogUtils.getLogger(this.getClass()).error(e.getMessage(), e);
		}
	}

}
