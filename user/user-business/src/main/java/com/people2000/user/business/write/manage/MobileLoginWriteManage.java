/**
 *
 */
package com.people2000.user.business.write.manage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.vo.MobileUserVO;

/**
 * @author crc
 * @time 2015-4-29 下午5:29:29
 * @description <pre>
 * <p/>
 * </pre>
 */
public interface MobileLoginWriteManage {
	/**
	 * 登陆
	 * 
	 * @param user
	 * @return
	 * @description <pre>
	 * 
	 * </pre>
	 */
	HashMap<String, Object> login(MobileUserVO user, HttpServletRequest req);

	/**
	 * 手机验证码登录
	 *
	 * @param user
	 * @return
	 */
	HashMap<String, Object> loginWithPhone(MobileUserVO user,
			HttpServletRequest req);

	HashMap<String, Object> loginWithPhoneAndPwd(MobileUserVO user,
			HttpServletRequest req);

	/**
	 * 写登陆历史
	 * 
	 * @param map
	 * @throws Exception
	 * @description <pre>
	 * 
	 * </pre>
	 */
	Long insertLoginLogWithTx(HashMap<String, Object> map) throws Exception;

	/**
	 * 写用户登陆cookie
	 * 
	 * @param id
	 * @param userLoginId
	 * @return
	 * @description <pre>
	 * 
	 * </pre>
	 */
	UserCookie inserUserCookieWithTx(Long id, Long userLoginId)
			throws Exception;

	public UserCache getCacheUser(User user) throws Exception;

	/**
	 * 登录异常日志
	 * 
	 * @param code
	 * @param req
	 * @description <pre>
	 * 
	 * </pre>
	 */
	void insertLoginExceptionLogWithTx(int code, String userName, String ip,
			String userAgent) throws Exception;

	/**
	 * 触发首次APP登录，积分校验规则
	 * 
	 * @author wupeng
	 * @date 2016/10/19
	 * @param
	 * @param user
	 * @return
	 * @throws
	 */
	void sendLoginInfoToMQ(Map<String, Object> user);

	void updateOpenIdWithTx(MobileUserVO user);
}
