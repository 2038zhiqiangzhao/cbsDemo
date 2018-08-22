/**
 *
 */
package com.people2000.user.business.write.manage.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.dao.ext.UserLoginDAOWrite2;
import com.people2000.user.business.write.manage.UserLoginWriteManage;
import com.people2000.user.model.dto.UserInputDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UserLogin;
import com.people2000.user.model.po.ibatis.UserLoginExample;

/**
 * @author hyf
 * @time 2015-8-25 下午12:27:02
 * @description <pre>
 * 用户登陆日志
 * </pre>
 */
@Service("userLoginWriteManage")
public class UserLoginWriteManageImpl implements UserLoginWriteManage {

	@Resource(name = "userLoginDAOWrite")
	private UserLoginDAOWrite2 userLoginDAOWrite;

	@Override
	public List<UserLogin> getUserLoginsByUserId(Long userId)
			throws SQLException {
		UserLoginExample userLoginExample = new UserLoginExample();
		UserLoginExample.Criteria criteria = userLoginExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userLoginDAOWrite.selectByExample(userLoginExample, false);
	}

	@Override
	public Boolean getUserFistLogin(UserInputDTO userInputDTO)
			throws OuserMangeException {
		try {
			List<Map<String, Object>> list = userLoginDAOWrite
					.getLoginLimit2(userInputDTO);
			if (list != null && list.size() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LogUtils.getLogger(UserLoginWriteManageImpl.class).error(
					e.getMessage(), e);
			throw new OuserMangeException("查询用户是否首次登录错误:" + e.getMessage());
		}
	}

}
