/**
 *
 */
package com.people2000.user.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.utils.CaptchaGenerator;
import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.ErrorCode;
import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.manage.MobileLoginWriteManage;
import com.people2000.user.business.write.manage.PushMessageWriteManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.vo.CheckImageVO;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.web.BaseAction;
import com.people2000.user.web.utils.CookieWrapper;
import com.people2000.user.web.utils.HeaderUtils;
import com.people2000.user.web.utils.ResponseUtils;
import com.people2000.user.web.utils.nodify.LoginNotifyUtil;
import com.people2000.user.web.utils.nodify.handler.LoginFailureProcessor;
import com.people2000.user.web.utils.nodify.handler.LoginSuccessProcessor;
import com.people2000.user.web.vo.UserTypeVO;

/**
 * @author xxl
 * @time 2015-10-8 下午5:20:10
 * @description 登陆
 */
@Controller
@RequestMapping(value = "/mobileLogin")
public class MobileLoginAction extends BaseAction implements
		ApplicationContextAware {

	private static final Logger log = LoggerFactory
			.getLogger(MobileLoginAction.class);

	private ApplicationContext applicationContext;
	private MobileLoginWriteManage loginWriteManage;
	@Resource(name = "userCookieWriteManage")
	private UserCookieWriteManage userCookieWriteManage;
	@Resource
	private PushMessageWriteManage pushMessageWriteManage;

	/**
	 * 登陆action
	 * 
	 * @param user
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody MobileUserVO user, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		loginWriteManage = applicationContext.getBean(
				"mobileLoginWriteManageImpl", MobileLoginWriteManage.class);

		log.debug("登陆方法被调用,用户名称：{}", user.getUsername());
		user = HeaderUtils.fillInDeviceId(req, user);

		// 是否有验证码
		if (user.isHasCheckImage()) {
			CookieWrapper cookies = new CookieWrapper(req.getCookies());
			String imgeKey = cookies.getCookieValue("imageKey");

			String captcha = user.getCheckImageCode();
			Object captchCache = CacheCommUtils.getCache().get(imgeKey);

			if (captchCache == null
					|| !captchCache.toString().equalsIgnoreCase(captcha)) {
				log.debug("图形验证码输入有误");
				return ResponseUtils.map("-2", "验证码输入有误");
			}
		}

		// D626-033 限制用户用邮箱登陆
		if (user.getUsername() != null && user.getUsername().indexOf("@") >= 0) {
			return ResponseUtils.map("-2", "请用手机号登陆！");
		}

		HashMap<String, Object> map = null;
		// 正常流程
		if (user.getMobile() != null && user.getCaptchas() != null) {
			map = loginWriteManage.loginWithPhone(user, req);
		} else {
			// 优先1username登录 2手机号登录 3邮箱登录
			if (user.getUsername() == null) {
				user.setUsername(user.getMobile());
			}
			if (user.getUsername() == null) {
				user.setUsername(user.getEmail());
			}
			map = loginWriteManage.login(user, req);
		}
		int code = Integer.parseInt(map.get("code").toString());// code 等于
		// 0表示登陆成功
		UserCookie userCookie = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (code == 0) { // 登陆成功
			UserCache cacheUser = (UserCache) map.get("data");
			// 写缓存 cookie
			userCookie = SsoClientUtil.login(cacheUser, req, resp);
			resultMap.put("ut", userCookie.getCookieValue());// 返回的是加密之后的ut
			// System.out.println("response的ut是:"+userCookie.getEncryptUt());
			final Integer identityType = cacheUser.getIdentityType();
			if (identityType != null && identityType != 0) {
				resultMap.put("identityType", identityType);// 返回服务人员类型
			}

			// 写登陆日志
			String ip = CommonUtils.getUserIp(req);
			String userAgent = req.getHeader("User-Agent");
			map.put("ip", ip);
			map.put("userAgent", userAgent);
			userCookie.setCompanyId(cacheUser.getCompanyId());

			LoginSuccessProcessor processor = new LoginSuccessProcessor(
					loginWriteManage, userCookieWriteManage, map, userCookie);
			LoginNotifyUtil.getInstance().submit(processor);

			// 判断是否有callback
			String callback = req.getParameter("callBack");
			if (StringUtils.isNotBlank(callback)) {
				resp.sendRedirect(callback);
			}

			// 触发首次APP登录，积分校验规则
			Map<String, Object> userAction = new HashMap<>();

			if (StringUtils.isNotBlank(user.getDeviceId())) {
				// 如果存在设备id，表示是app登录过来用户
				userAction.put("type", 5);
			} else {
				// 否则，表示是其他方式过来用户
				userAction.put("type", 2);
			}
			userAction.put("userId", cacheUser.getId());
			userAction.put("companyId", cacheUser.getCompanyId());
			loginWriteManage.sendLoginInfoToMQ(userAction);

			// 更新openId
			if (StringUtils.isNotBlank(user.getOpenId())) {
				MobileUserVO userVo = new MobileUserVO();
				userVo.setId(cacheUser.getId());
				userVo.setOpenId(user.getOpenId()); 
				loginWriteManage.updateOpenIdWithTx(userVo);
			}

		} else {
			String ip = CommonUtils.getUserIp(req);
			String userAgent = req.getHeader("User-Agent");
			LoginFailureProcessor processor = new LoginFailureProcessor(
					loginWriteManage, code, user.getUsername(), ip, userAgent);
			LoginNotifyUtil.getInstance().submit(processor);
		}
		resultMap.put("code", code);
		resultMap.put("message", map.get("message").toString());
		return resultMap;
	}

	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	@ResponseBody
	public Object loginForm(MobileUserVO user, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		return this.login(user, req, resp);
	}

	/**
	 * 生成图片验证码
	 * 
	 * @param checkImageVO
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/checkImage", method = RequestMethod.GET)
	@ResponseBody
	public void getCheckImage(@RequestBody CheckImageVO checkImageVO,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CaptchaGenerator captchaGenerator = new CaptchaGenerator();
		captchaGenerator.getCode(req, resp, checkImageVO.getWidth(),
				checkImageVO.getHeight(), checkImageVO.getCodeNmInSession(),
				checkImageVO.getCodeCount());
	}

	@RequestMapping(value = "/checkImageForm", method = RequestMethod.GET)
	@ResponseBody
	public void CheckImageVOForm(CheckImageVO checkImageVO,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		this.getCheckImage(checkImageVO, req, resp);
	}

	/**
	 * 获取用户信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public HashMap<String, Object> getUserInfo(@RequestParam String ut)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// if (StringUtils.isBlank(ut)) {
		// map.put("Code", 1);
		// map.put("message", "用户未登录");
		// return map;
		// }
		map.put("Code", 0);
		map.put("message", "成功");
		map.put("username", SessionContainer.getSession().getUsername());
		return map;
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
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exit")
	@ResponseBody
	public Object exit(@RequestBody MobileUserVO user, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String ut = SsoClientUtil.getUt(req);
		if (StringUtils.isNotEmpty(ut)) {
			SsoClientUtil.logout(ut);
			// SsoClientUtil.logout(req, resp);// 清除cookie
			userCookieWriteManage.logoutWithTx(ut);
		}
		String deviceId = req.getParameter("deviceId");
		if (StringUtils.isEmpty(deviceId)) {
			deviceId = HeaderUtils.getHeader(req).get("deviceId");
		}
		if (StringUtils.isEmpty(deviceId)) {
			deviceId = user.getDeviceId();
		}

		if (StringUtils.isNotEmpty(deviceId)) {// 清空登录设备信息
			pushMessageWriteManage.delAllPushMessageWithTx(deviceId);
		}
		return success(null);
	}

	// 后面这个方法会进行删除
	public Object changeUserType(UserTypeVO userTypeVO, UserCache userCache)
			throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		if (userCache != null) {
			// 更新用户类型
			userCache.setType(userTypeVO.getUserType());

			SsoClientUtil.login(userCache, userTypeVO.getUt());

		}
		result.put("code", ErrorCode.SUCCESS);
		result.put("message", "操作成功");
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
