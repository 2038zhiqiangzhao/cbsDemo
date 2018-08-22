/**
 * 
 */
package com.people2000.user.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.manage.LoginWriteManage;
import com.people2000.user.business.write.manage.UnionLoginManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.web.BaseAction;

/**
 * @author crc
 * @time 2015-4-29 下午5:20:10
 * @description <pre>
 * 登陆
 * </pre>
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAction extends BaseAction {

	@Resource(name = "loginWriteManageImpl")
	private LoginWriteManage loginWriteManage;

	@Resource(name = "userCookieWriteManage")
	private UserCookieWriteManage userCookieWriteManage;

	@Resource
	private UnionLoginManage unionLoginManage;

	/**
	 * 登陆
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody User user, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		LogUtils.getLogger(LoginAction.class).debug(
				"登陆方法被调用,用户名称：" + user.getUsername());
		HashMap<String, Object> map = loginWriteManage.login(user);
		int code = Integer.parseInt(map.get("code").toString());// code 等于
																// 0表示登陆成功

		if (code == 0) { // 登陆成功
			UserCache cacheUser = (UserCache) map.get("data");
			// 写缓存 cookie
			UserCookie userCookie = SsoClientUtil.login(cacheUser, req, resp);
			// 写登陆日志
			Long userLoginId = loginWriteManage.insertLoginLogWithTx(map, req);
			// 写登陆cookie表
			userCookie.setUserLoginId(userLoginId);
			userCookie.setCompanyId(cacheUser.getCompanyId());
			userCookieWriteManage.saveUserCookieWithTx(userCookie);
		} else {
			loginWriteManage.insertLoginExceptionLogWithTx(code,
					user.getUsername(), req);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", code);
		return resultMap;
	}

	/**
	 * 获取权限
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/function")
	@ResponseBody
	public Object getFunction() throws Exception {
		return SessionContainer.getfunctionCodes();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exit")
	@ResponseBody
	public Object exit(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		SsoClientUtil.logout(req, resp);
		return success("");
	}

	/**
	 * 用户绑定
	 * 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userBind", method = RequestMethod.POST)
	@ResponseBody
	public Object unionLogin(MobileUserVO userVO) throws Exception {
		Map<String, Object> returnMap = null;
		try {

		} catch (Exception e) {

		}
		return returnMap;
	}

	@RequestMapping(value = "/getUserCache")
	@ResponseBody
	public Object queryUserCache(@RequestParam String ut) throws Exception {
		UserCache userCache = null;
		try {
			userCache = userCookieWriteManage.getUserByLoginCookieWithTx(ut);
		} catch (Exception e) {
		}
		return userCache;
	}
}
