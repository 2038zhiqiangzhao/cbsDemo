package com.people2000.user.business.write.manage;

import java.sql.SQLException;

import com.people2000.user.business.utils.security.EncryptEntity;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserExample;

/**
 * 
 * @author xuc
 * @time 2016年5月18日 下午3:23:56
 * @description 密码加密相关信息接口
 */

public interface PasswordEncryptInfoWriteManage {

	/**
	 * 查询加密算法信息
	 * 
	 * @param User
	 * @see @link User
	 * @return EncryptEntity
	 * @see @link EncryptEntity
	 */
	EncryptEntity getEncryptInfoByUser(User user) throws SQLException;

	/**
	 * 更新用户加密算法信息
	 * 
	 * @param User
	 * @param UserExample
	 * @see @link User UserExample
	 * @return int
	 */
	int updateEncryptInfoByUserWithTx(User user, UserExample userExample)
			throws SQLException;

}
