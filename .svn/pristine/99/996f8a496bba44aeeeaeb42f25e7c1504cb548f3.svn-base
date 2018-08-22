/**
 * 
 */
package com.people2000.user.business.write.manage;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;

/**
 * @author crc
 * @time 2015-4-29 下午5:29:29
 * @description <pre>
 * 
 * </pre>
 *
 */
public interface LoginWriteManage {

	/**
	 * 
	 * @description <pre>
	 * 登陆
	 * </pre>
	 * @param user
	 * @return
	 */
	HashMap<String, Object> login(User user);

	/**
	 * 
	 * @description <pre>
	 * 写登陆历史
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	Long insertLoginLogWithTx(HashMap<String, Object> map,
			HttpServletRequest req) throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 写用户登陆cookie
	 * </pre>
	 * @param id
	 * @param userLoginId
	 * @return
	 */
	UserCookie inserUserCookieWithTx(Long id, Long userLoginId)
			throws Exception;

	public UserCache getCacheUser(User user) throws Exception;

	/**
	 * 
	 * @description <pre>
	 * 登录异常日志
	 * </pre>
	 * @param code
	 * @param req
	 */
	void insertLoginExceptionLogWithTx(int code, String userName,
			HttpServletRequest req) throws Exception;
}
