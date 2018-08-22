package com.people2000.user.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.manage.MobileLoginWriteManage;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.web.utils.HeaderUtils;

/**
 * Created by xiaole on 2015/12/5.
 */
@Controller
public class RegisterEntryAction {
	@Value("${user.condition}")
	private String condition = "release";
	@Resource(name = "mobileUserWriteManage")
	private MobileUserWriteManage mobileUserWriteManage;

	@Resource(name = "mobileLoginWriteManageImpl")
	private MobileLoginWriteManage loginWriteManage;
	@Resource(name = "userCookieWriteManage")
	private UserCookieWriteManage userCookieWriteManage;

	/**
	 * 后台注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/backRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserBack(@RequestBody MobileUserVO userDto)
			throws Exception {
		// TODO 必填校验
		userDto.setType(ConstantUser.user_type.backend);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 逻辑处理
		try {
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userDto);
			return map;

		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

	@RequestMapping(value = "/backRegisterForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserBackForm(MobileUserVO userDto) throws Exception {
		return this.submitUserBack(userDto);
	}

	/**
	 * 商家注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserMerchant(@RequestBody MobileUserVO userDto)
			throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String code = null;

		// blbl需求修改,不能注册
		if (null != userDto && null != userDto.getCompanyId()
				&& userDto.getCompanyId() == 4l) {
			resultMap.put("code", "-1");
			resultMap.put("message", "本来便利不提供注册服务!");
			return resultMap;
		}
		resultMap = mobileUserWriteManage.checkCodeWithTx(userDto);
		code = resultMap.get("code").toString();
		if (code != null && !code.equals("0")) {
			return resultMap;
		}
		userDto.setType(ConstantUser.user_type.main);
		try {
			// 逻辑处理
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userDto);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

	@RequestMapping(value = "/mainRegisterForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserMerchantForm(MobileUserVO userDto) throws Exception {
		return this.submitUserMerchant(userDto);
	}

	/**
	 * 商家注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainRegisterNoMobile", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserMerchantNoMobile(@RequestBody MobileUserVO userDto,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO lws:各种map做为返回值，很爽，但是不清晰，有两三个返回值改用Tuple,Triple，再多是不是应该返回一个对象?
		HashMap<String, Object> isReat = mobileUserWriteManage
				.isRepeatPhone(userDto);
		Integer code = (Integer) isReat.get("code");
		if (!Integer.valueOf(0).equals(code)) {
			return isReat;
		}
		// TODO 必填校验
		HashMap<String, Object> map = new HashMap<String, Object>();
		if ("development".equals(condition)) {// TODO lws:只有开发环境采用注册验证码？
			// 是否有验证码 TODO lws: 是否要校验码的逻辑为什么居然是来自入参的？
			if (userDto.isHasCheckImage()) {
				// 判断图形验证码是否正确 TODO lws: LogUtils一点意义都没有
				LogUtils.getLogger(this.getClass()).debug(
						"userDto.getCodeNmInSession() = "
								+ userDto.getCodeNmInSession());
				LogUtils.getLogger(this.getClass()).debug(
						"userDto.getCheckImageCode() = "
								+ userDto.getCheckImageCode());
				LogUtils.getLogger(this.getClass()).debug(
						"session_id = " + req.getSession().getId());

				// String codeNmInSession = userDto.getCodeNmInSession();
				String checkImageCode = userDto.getCheckImageCode();
				// if(codeNmInSession == null){
				// codeNmInSession = "VALIDATE_CODE";
				// }
				// session中 的数据 为了 分布式共享 全部放到memcache中
				// TODO lws:
				// cookie获取改用cookiewrapper、为什么验证码cache的cache居然来自cookie？
				Cookie[] cookieArr = req.getCookies();
				String cookieVal = "";
				for (Cookie cookie : cookieArr) {
					if ("imageKey".equals(cookie.getName())) {
						cookieVal = cookie.getValue();
					}
				}

				if (StringUtils.isBlank(cookieVal)) {
					map.put("code", "-2");
					map.put("message", "验证码输入有误");
					return map;
				}

				String sessionCode = CacheCommUtils.getCache(cookieVal)
						.toString();
				// String sessionCode =
				// req.getSession().getAttribute(codeNmInSession) == null ? "" :
				// req.getSession().getAttribute(codeNmInSession).toString();
				LogUtils.getLogger(this.getClass()).info(
						"sessionCode = " + sessionCode);

				if (checkImageCode == null
						|| !checkImageCode.equalsIgnoreCase(sessionCode)) {
					map.put("code", "-2");
					map.put("message", "验证码输入有误");
					return map;
				}
			}
		}
		userDto.setType(ConstantUser.user_type.main);
		// 逻辑处理
		try {
			map = mobileUserWriteManage.registerWithTx(userDto);
		} catch (Exception e) {
			map.put("code", "-1");
			map.put("message", e.getMessage());
			return map;
		}

		if (map != null && map.size() > 0 && map.get("code") != null
				&& (Integer) map.get("code") == 20) {
			try {
				userDto.setUsername(userDto.getMobile());
				userDto.setPassword(userDto.getConfirmPassword());
				login(userDto, req, resp);
			} catch (Exception e) {
				LogUtils.getLogger(this).error("自动登陆失败", e);
			}
		}

		return map;
	}

	/**
	 * 用户注册后自动使用账号密码登陆
	 * 
	 * @param user
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void login(MobileUserVO user, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		LogUtils.getLogger(this).info("自动登陆");
		HashMap<String, Object> map = loginWriteManage.login(user, req);
		int code = Integer.parseInt(map.get("code").toString());// code 等于
		// 0表示登陆成功
		if (code != 0)
			return;
		// 登陆成功
		UserCookie userCookie = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 登陆成功
		UserCache cacheUser = (UserCache) map.get("data");
		// 写缓存 cookie
		userCookie = SsoClientUtil.login(cacheUser, req, resp);
		resultMap.put("ut", userCookie.getCookieValue());// 返回ut
		final Integer identityType = cacheUser.getIdentityType();
		if (identityType != null && identityType != 0) {
			resultMap.put("identityType", identityType);// 返回服务人员类型
		}
		// 写登陆日志
		String ip = CommonUtils.getUserIp(req);
		String userAgent = req.getHeader("User-Agent");
		map.put("ip", ip);
		map.put("userAgent", userAgent);
		Long userLoginId = loginWriteManage.insertLoginLogWithTx(map);
		// 写登陆cookie表
		userCookie.setUserLoginId(userLoginId);
		userCookie.setCompanyId(cacheUser.getCompanyId());
		userCookieWriteManage.saveUserCookieWithTx(userCookie);
	}

	@RequestMapping(value = "/mainRegisterNoMobileForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserMerchantNoMobileForm(MobileUserVO userDto,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return this.submitUserMerchantNoMobile(userDto, req, resp);
	}

	/**
	 * 商家子账户
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/childRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserChild(@RequestBody MobileUserVO userDto)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// TODO 必填校验
		userDto.setType(ConstantUser.user_type.child);
		// 逻辑处理
		try {
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userDto);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

	@RequestMapping(value = "/childRegisterForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserChildForm(MobileUserVO userDto) throws Exception {
		return this.submitUserChild(userDto);
	}

	/**
	 * 会员注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/memberRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserMember(@RequestBody MobileUserVO userDto)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		userDto.setType(ConstantUser.user_type.member);
		// 逻辑处理
		try {
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userDto);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

	@RequestMapping(value = "/memberRegisterForm", method = RequestMethod.POST)
	@ResponseBody
	/*
	 * public Object submitUserMemberForm(MobileUserVO userDto) throws Exception
	 * { return this.submitUserMember(userDto); }
	 */
	public Object submitUserMemberForm(MobileUserVO userDto,
			HttpServletRequest req) throws Exception {
		if (userDto != null) {
			userDto.setDeviceId(HeaderUtils.getDeviceId(req));
		}
		return this.submitUserMember(userDto);
	}

	/**
	 * 会员注册(校验号码)
	 *
	 * @param userAndCaptchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/memberRegisterAndCheck", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserMemberAndCheck(
			@RequestBody MobileUserVO userAndCaptchas) throws Exception {
		// TODO 必填校验
		userAndCaptchas.setType(ConstantUser.user_type.member);
		HashMap<String, Object> resultMap = mobileUserWriteManage
				.checkCodeWithTx(userAndCaptchas);
		String code = resultMap.get("code").toString();
		if (code != null && !code.equals("0")) {
			return resultMap;
		}
		// 逻辑处理
		try {
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userAndCaptchas);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

	@RequestMapping(value = "/memberRegisterAndCheckForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserMemberAndCheckForm(MobileUserVO userDto)
			throws Exception {
		return this.submitUserMerchant(userDto);
	}

	/**
	 * 企业用户注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/businessRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserBusiness(@RequestBody MobileUserVO userDto)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// TODO 必填校验
		userDto.setType(ConstantUser.user_type.business);
		// 逻辑处理
		try {
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userDto);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

	@RequestMapping(value = "/businessRegisterForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserBusinessForm(MobileUserVO userDto) throws Exception {
		return this.submitUserBusiness(userDto);
	}

	// 用户管理员注册

	/**
	 * 服务用户注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserService(@RequestBody MobileUserVO userDto)
			throws Exception {
		// 校验手机重复注册
		HashMap<String, Object> isReat = mobileUserWriteManage
				.isRepeatPhone(userDto);
		Integer code = (Integer) isReat.get("code");
		if (!code.equals(0)) {
			return isReat;
		}

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// TODO 必填校验
		userDto.setType(ConstantUser.user_type.service);
		// 逻辑处理
		try {
			HashMap<String, Object> map = mobileUserWriteManage
					.registerWithTx(userDto);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

	@RequestMapping(value = "/serviceRegisterForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserServicesForm(MobileUserVO userDto) throws Exception {
		return this.submitUserService(userDto);
	}

	@RequestMapping(value = "/getCaptchasByMobile", method = RequestMethod.GET)
	@ResponseBody
	public Object getCaptchasByMobile(MobileUserVO userDto) throws Exception {
		return mobileUserWriteManage.getCaptchasByMobile(userDto);
	}
}
