/**
 *
 */
package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.dto.UserInputDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UserLogin;

/**
 * @author hyf
 * @time 2015-8-25 下午12:26:35
 * @description <pre>
 * 用户登录日志
 * </pre>
 */
public interface UserLoginWriteManage {

	// 根据用户id获取所有登录日志
	public List<UserLogin> getUserLoginsByUserId(Long userId)
			throws SQLException;

	/**
	 * 根据userId获取是否为首次登录.
	 * 
	 * @param userInputDTO
	 * @return
	 * @throws OuserMangeException
	 */
	Boolean getUserFistLogin(UserInputDTO userInputDTO)
			throws OuserMangeException;
}
