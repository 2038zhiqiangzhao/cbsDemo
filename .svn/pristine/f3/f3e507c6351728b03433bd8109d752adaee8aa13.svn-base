package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.people2000.user.business.utils.SaltUtils;
import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.manage.EncryptInfoWriteManage;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;

/***
 * 
 * @author xuc
 * @time 2016年5月18日 下午3:34:14
 * @description 密码加密相关信息类
 */

@Service("encryptInfoWriteManage")
public class EncryptInfoWriteManageImpl implements EncryptInfoWriteManage {

	private Logger logger = Logger.getLogger(EncryptInfoWriteManageImpl.class);

	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	/**
	 * 查询加密算法信息
	 * 
	 * @param User
	 * @see @link User
	 * @return EncryptEntity
	 * @see @link EncryptEntity
	 */
	@Override
	public EncryptEntity getEncryptInfoByUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		EncryptEntity encryptEntity = new EncryptEntity();
		if (null == user) {// 如果传入的user是null,则查询平台当前的算法信息
			// 后面是查平台表,这里先默认
			encryptEntity.setNewFSalt(SaltUtils.getRandomSalt(10));
			encryptEntity.setNewFVersion(1);

			encryptEntity.setNewBSalt(SaltUtils.getRandomSalt(10));
			encryptEntity.setNewBVersion(1);

			// 没有userId,置为-1
			encryptEntity.setUserId(-1);

			logger.info("查询系统当前的后端加密算法跟盐值");
		} else {
			user.setIsDeleted(0);
			List<User> list = userDAOWrite.selectListUserByuser(user);
			if (list.isEmpty()) {
				// 将用户名作为手机号查询一下
				if (StringUtils.isNotEmpty(user.getUsername())) {
					user.setMobile(user.getUsername());
					user.setUsername(null);

					list = userDAOWrite.selectListUserByuser(user);
				}
			}

			if (!list.isEmpty()) {
				// 这里后面需要与平台的前后端算法和有效期比较,现在暂时默认

				User ret = list.get(0);// 这里直接get(0),如果重复管不着了

				// 前端算法盐值信息
				if (null == ret.getfAlgorithmVersion()) {
					encryptEntity.setNewFVersion(1);
				} else {
					// 后面需要跟平台当前算法比较
					if (1 != 1) {
						encryptEntity.setNewFVersion(1);
					}
					encryptEntity.setOldFVersion(ret.getfAlgorithmVersion());

				}

				if (StringUtils.isEmpty(ret.getfSalt())) {
					encryptEntity.setNewFSalt(SaltUtils.getRandomSalt(10));
				} else {
					if (null == ret.getfSaltUpdateTime()) {
						encryptEntity.setNewFSalt(SaltUtils.getRandomSalt(10));
						encryptEntity.setOldFSalt(ret.getfSalt());

					} else {
						Date date = ret.getfSaltUpdateTime();
						if ((((new Date()).getTime() - date.getTime()) / 86400000) >= 7) {// 这里默认7天
							encryptEntity.setOldFSalt(ret.getfSalt());
							encryptEntity.setNewFSalt(SaltUtils
									.getRandomSalt(10));

						} else {
							encryptEntity.setOldFSalt(ret.getfSalt());
						}
					}
				}

				// 后端算法盐值信息
				if (null == ret.getbAlgorithmVersion()) {
					encryptEntity.setNewBVersion(1);
				} else {
					// 后面需要跟平台当前算法比较
					if (1 != 1) {
						encryptEntity.setNewBVersion(1);
					}
					encryptEntity.setOldBVersion(ret.getbAlgorithmVersion());
				}

				if (StringUtils.isEmpty(ret.getbSalt())) {
					encryptEntity.setNewBSalt(SaltUtils.getRandomSalt(10));
				} else {
					if (null == ret.getbSaltUpdateTime()) {
						encryptEntity.setNewBSalt(SaltUtils.getRandomSalt(10));
						encryptEntity.setOldBSalt(ret.getbSalt());
					} else {
						Date date = ret.getbSaltUpdateTime();
						if ((((new Date()).getTime() - date.getTime()) / 86400000) >= 7) {// 这里默认7天
							encryptEntity.setOldBSalt(ret.getbSalt());
							encryptEntity.setNewBSalt(SaltUtils
									.getRandomSalt(10));

						} else {
							encryptEntity.setOldBSalt(ret.getbSalt());
						}
					}
				}

				// 返回user的id
				encryptEntity.setUserId(ret.getId());

			} else {// 没有查询到结果
				encryptEntity.setNewFSalt(SaltUtils.getRandomSalt(10));
				encryptEntity.setNewFVersion(1);

				encryptEntity.setNewBSalt(SaltUtils.getRandomSalt(10));
				encryptEntity.setNewBVersion(1);

				// 没有userId,置为-1
				encryptEntity.setUserId(-1);

			}

		}
		return encryptEntity;
	}

	/**
	 * 更新用户加密算法信息
	 * 
	 * @param User
	 * @param UserExample
	 * @see @link User UserExample
	 * @return int
	 */
	@Override
	public int updateEncryptInfoByUserWithTx(User user, UserExample userExample)
			throws SQLException {
		int cnt = 0;
		if (userExample != null) {
			cnt = userDAOWrite.updateByExampleSelective(user, userExample);
		} else {
			cnt = userDAOWrite.updateByPrimaryKeySelective(user);
		}
		return cnt;
	}

}
