/**
 *
 */
package com.people2000.user.business.write.manage;

import java.sql.SQLException;
import java.util.List;

import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.UserCookie;

/**
 * @author crc
 * @time 2015-4-30 下午12:26:35
 * @description <pre>
 * 用户cookie
 * </pre>
 */
public interface UserCookieWriteManage {

	/**
	 * @param cookieValue
	 * @return
	 * @description <pre>
	 * 根据cookies获取用户信息
	 * </pre>
	 */
	UserCache getUserByLoginCookieWithTx(String cookieValue) throws Exception;

	void saveUserCookieWithTx(UserCookie userCookie) throws Exception;

	/**
	 * 根据ut删除cookie
	 *
	 * @param ut
	 */
	void logoutWithTx(String ut);

	// 根据用户id获取所有ut
	public List<UserCookie> getUserCookiesByIdWithTx(Long id)
			throws SQLException;
}
