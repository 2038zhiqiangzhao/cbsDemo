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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.utils.CacheKeyUtils;
import com.people2000.user.business.utils.CaptchaGenerator;
import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.vo.CheckImageVO;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.web.BaseAction;
import com.people2000.user.web.utils.HeaderUtils;

/**
 * @author xxl
 * @time 2015-10-8 下午6:26:52
 * @description <pre>
 * 注册
 * </pre>
 */
@Controller
@RequestMapping(value = "/mobileRegister")
public class MobileRegisterAction extends BaseAction {

	@Value("${user.condition}")
	private String condition = "release";

	@Resource(name = "mobileUserWriteManage")
	private MobileUserWriteManage mobileUserWriteManage;

	private Logger logger = LogUtils.getLogger(MobileRegisterAction.class);

	/**
	 * 注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUser(@RequestBody MobileUserVO userDto)
			throws Exception {
		// 逻辑处理
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
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

	@RequestMapping(value = "/registerForm", method = RequestMethod.POST)
	@ResponseBody
	public Object submitUserForm(MobileUserVO userDto) throws Exception {
		return this.submitUser(userDto);
	}

	/**
	 * 修改密码
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyPassword", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPassword(@RequestBody MobileUserVO userDto)
			throws Exception {
		Long userId = null;
		HashMap<String, Object> map = new HashMap<String, Object>();

		// blbl需求修改,不提供改密服务
		if (null != userDto && null != userDto.getCompanyId()
				&& userDto.getCompanyId() == 4l) {
			map.put("code", "-1");
			map.put("message", "本来便利不提供改密服务!");
			return map;
		}
		try {
			// 未经过loginfilter时, SessionContainer 会报空指针,再用ut获取.
			userId = getUserId(userDto.getUt());
			userDto.setId(userId);
			userDto.setType(4);
			map = mobileUserWriteManage.updatePassWordWithTx(userDto);
		} catch (OuserMangeException e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			map.put("code", "96");
			map.put("message", "密码修改错误:" + e.getMessage());
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			map.put("code", "96");
			map.put("message", "密码修改错误,系统错误.");
		}

		return map;
	}

	@RequestMapping(value = "/modifyPasswordForm", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPasswordForm(MobileUserVO userDto) throws Exception {
		return this.modifyPassword(userDto);
	}

	/**
	 * 修改密码
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyPasswordOnline", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPasswordOnline(@RequestBody MobileUserVO userDto)
			throws Exception {
		userDto.setId(SessionContainer.getUserId());
		final String mobile = SessionContainer.getMobile();
		HashMap<String, Object> map = mobileUserWriteManage
				.updatePassWordWithTx(userDto);
		map.put("mobile", mobile);
		return map;
	}

	@RequestMapping(value = "/modifyPasswordOnlineForm", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPasswordOnlineForm(MobileUserVO userDto)
			throws Exception {
		return this.modifyPasswordOnline(userDto);
	}

	/**
	 * 校验用户名和验证码
	 * 
	 * @param userVO
	 * @return Object
	 */
	@RequestMapping(value = "/checkNameAndCaptchas", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object checkNameAndCaptchas(@RequestBody MobileUserVO userVO)
			throws Exception {
		HashMap<String, Object> resultMap = mobileUserWriteManage
				.checkUsernameAndMobile(userVO);
		String code = resultMap.get("code").toString();
		if (code != null && !code.equals("0")) {
			return resultMap;
		}
		resultMap = mobileUserWriteManage.checkCodeWithTx(userVO);
		return resultMap;
	}

	@RequestMapping(value = "/checkNameAndCaptchasForm", method = RequestMethod.POST)
	@ResponseBody
	public Object checkNameAndCaptchasForm(MobileUserVO userVO)
			throws Exception {
		return this.checkNameAndCaptchas(userVO);
	}

	/**
	 * 发送验证码
	 *
	 * @param captchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendCaptchas", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object sendCode(@RequestBody MobileUserVO captchas) throws Exception {
		HashMap<String, Object> resultMap = null;
		if ("development".equals(condition)) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("code", 0);
			resultMap.put("message", "校验码发送成功!");

			return resultMap;
		}
		resultMap = mobileUserWriteManage.sendCodeWithTx(captchas);
		return resultMap;
	}

	@RequestMapping(value = "/sendCaptchasForm", method = RequestMethod.POST)
	@ResponseBody
	public Object sendCodeForm(MobileUserVO captchas) throws Exception {
		return this.sendCode(captchas);
	}

	@RequestMapping(value = "/sendSmsCaptchaToBindedMobile")
	@ResponseBody
	public Object sendSmsCaptchaToBindedMobile(MobileUserVO captchas)
			throws Exception {

		if (captchas == null || captchas.getMobile() == null) {
			return resultMap(8, "手机号不能为空");
		}

		UserCache user = SessionContainer.getSession();
		if (user == null || user.getId() == null || user.getId() < 1) {
			return resultMap(99, "请先登录");
		}

		if (!captchas.getMobile().equals(user.getMobile())) {
			return resultMap(10, "输入的手机号必须与绑定的手机号一致");
		}

		return sendCodeForm(captchas);
	}

	/**
	 * 发送验证码
	 *
	 * @param captchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendCaptchasCode", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object sendCaptchasCode(@RequestBody MobileUserVO captchas,
			HttpServletRequest request) throws Exception {
		HashMap<String, Object> resultMap = mobileUserWriteManage
				.sendCaptchasWithTx(captchas, request);
		return resultMap;
	}

	@RequestMapping(value = "/sendCaptchasCodeForm", method = RequestMethod.POST)
	@ResponseBody
	public Object sendCaptchasCodeForm(MobileUserVO captchas,
			HttpServletRequest request) throws Exception {
		return this.sendCaptchasCode(captchas, request);
	}

	@RequestMapping(value = "/checkImageForm", method = RequestMethod.POST)
	@ResponseBody
	public Object checkImageForm(CheckImageVO checkImageVO,
			HttpServletRequest request) throws Exception {
		CaptchaGenerator captchaGenerator = new CaptchaGenerator();
		// TODO dusai
		// HashMap<String, Object> resultMap
		// =captchaGenerator.getCheckImage(request, checkImageVO.getWidth(),
		// checkImageVO.getHeight(), checkImageVO.getCodeNmInSession(),
		// checkImageVO.getCodeCount());
		return null;
	}

	/**
	 * 校验验证码
	 *
	 * @param captchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkCaptchas", method = RequestMethod.POST)
	@ResponseBody
	public HashMap checkCode(@RequestBody MobileUserVO captchas)
			throws Exception {
		LogUtils.getLogger(getClass())
				.info("checkCode----------------------------------------------------");
		LogUtils.getLogger(getClass()).info(
				"captchas=" + captchas.getCaptchas() + "|" + "mobile="
						+ captchas.getMobile());
		HashMap<String, Object> resultMap = mobileUserWriteManage
				.checkCodeWithTx(captchas);
		return resultMap;
	}

	@RequestMapping(value = "/checkCaptchasForm", method = RequestMethod.POST)
	@ResponseBody
	public Object checkCodeForm(MobileUserVO captchas, HttpServletRequest req)
			throws Exception {
		if (captchas != null) {
			captchas.setDeviceId(HeaderUtils.getDeviceId(req));
		}
		return this.checkCode(captchas);
	}

	/**
	 * 校验验证码并注册
	 *
	 * @param userAndCaptchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkAndRegister", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object checkCodeAndRegister(
			@RequestBody MobileUserVO userAndCaptchas, HttpServletRequest req)
			throws Exception {
		HashMap<String, Object> resultMap = mobileUserWriteManage
				.checkCodeWithTx(userAndCaptchas);
		String code = resultMap.get("code").toString();
		if (code != null && !code.equals("0")) {
			return resultMap;
		}
		userAndCaptchas = HeaderUtils.fillInDeviceId(req, userAndCaptchas);
		try {
			resultMap = mobileUserWriteManage.registerWithTx(userAndCaptchas);
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
		return resultMap;
	}

	@RequestMapping(value = "/checkAndRegisterForm", method = RequestMethod.POST)
	@ResponseBody
	public Object checkCodeAndRegisterForm(MobileUserVO userAndCaptchas,
			HttpServletRequest req) throws Exception {
		return this.checkCodeAndRegister(userAndCaptchas, req);
	}

	@RequestMapping(value = "/isRepeatPhoneForm", method = RequestMethod.POST)
	@ResponseBody
	public Object isRepeatPhoneForm(MobileUserVO userAndCaptchas)
			throws Exception {
		long start = System.currentTimeMillis();
		HashMap<String, Object> resultMap = new HashMap<>();
		// 入参非空验证
		if (userAndCaptchas == null
				|| StringUtils.isBlank(userAndCaptchas.getMobile())) {
			resultMap.put("code", -3);
			resultMap.put("message", "请求参数不完整");
			return resultMap;
		}

		resultMap = mobileUserWriteManage.isRepeatPhone(userAndCaptchas);
		LogUtils.getLogger(this.getClass()).info(
				"isRepeatPhoneForm接口调用耗时["
						+ (System.currentTimeMillis() - start) + "]毫秒");
		return resultMap;
	}

	@RequestMapping(value = "/forgetPassPhoneCheckForm", method = RequestMethod.POST)
	@ResponseBody
	public Object forgetPassPhoneCheckForm(MobileUserVO userAndCaptchas)
			throws Exception {
		long start = System.currentTimeMillis();
		HashMap<String, Object> resultMap = mobileUserWriteManage
				.isRepeatPhoneFormLocal(userAndCaptchas);
		LogUtils.getLogger(this.getClass()).info(
				"forgetPassPhoneCheckForm接口调用耗时["
						+ (System.currentTimeMillis() - start) + "]毫秒");
		return resultMap;
	}

	/**
	 * 根据ut和密码校验用户
	 *
	 * @param userAndCaptchas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkPasswordByUtForm", method = RequestMethod.POST)
	@ResponseBody
	public Object checkPasswordByUt(MobileUserVO userAndCaptchas)
			throws Exception {
		final Long userId = SessionContainer.getUserId();
		final String passwordKey = CacheKeyUtils.getPasswordKey(String
				.valueOf(userId));
		final Object cache = CacheCommUtils.getCache(passwordKey);
		if (cache == null) {
			CacheCommUtils.addCache(passwordKey, false);
		}
		userAndCaptchas.setId(userId);
		boolean b = mobileUserWriteManage.checkPasswordByUt(userAndCaptchas);
		Map<String, Object> map = new HashMap<String, Object>();
		if (b) {
			CacheCommUtils.putCache(passwordKey, true);
			map.put("code", "0");
			map.put("message", "验证成功");
			return map;
		}
		CacheCommUtils.putCache(passwordKey, false);
		map.put("code", "-1");
		map.put("message", "验证失败");
		return map;
	}

	/**
	 * 校验是否验证密码，并修改手机号码
	 *
	 * @return
	 */
	@RequestMapping(value = "/checkPwdAndModifyMobileForm", method = RequestMethod.POST)
	@ResponseBody
	public Object checkPwdAndModifyMobile(MobileUserVO userAndCaptchas,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		LogUtils.getLogger(getClass()).info(
				"captchas=" + userAndCaptchas.getCaptchas() + "|" + "mobile="
						+ userAndCaptchas.getMobile());
		HashMap map = checkCode(userAndCaptchas);
		final HashMap resultMap = new HashMap();
		String code = map.get("code").toString();
		if (!"0".equals(code)) {
			return map;
		}
		final Long userId = SessionContainer.getUserId();
		userAndCaptchas.setId(userId);
		final String passwordKey = CacheKeyUtils.getPasswordKey(String
				.valueOf(userId));
		final Object cache = CacheCommUtils.getCache(passwordKey);
		if (cache instanceof Boolean) {
			if (true == (Boolean) cache) {
				final HashMap<String, Object> result = mobileUserWriteManage
						.isRepeatPhone(userAndCaptchas);
				if (!"0".equals(result.get("code").toString())) {
					return result;
				}
				mobileUserWriteManage.modifyMobileWithTx(userAndCaptchas);
				resultMap.put("code", "0");
				resultMap.put("message", "修改成功");
				CacheCommUtils.putCache(passwordKey, false);

				// 清除缓存
				String ut = SsoClientUtil.getUt(req);
				SsoClientUtil.logout(ut);

				return resultMap;
			}
		}
		resultMap.put("code", "-1");
		resultMap.put("message", "密码未校验");
		return resultMap;
	}

	/**
	 * 校验是否验证手机号，并修改手机号码
	 *
	 * @return
	 */
	@RequestMapping(value = "/checkMobileAndModifyMobileForm", method = RequestMethod.POST)
	@ResponseBody
	public Object checkMobileAndModifyMobileForm(MobileUserVO userAndCaptchas,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		logger.info("换绑手机号码参数：" + JSON.toJSONString(userAndCaptchas));

		HashMap map = checkCode(userAndCaptchas);
		final HashMap resultMap = new HashMap();
		String code = map.get("code").toString();
		if (!"0".equals(code)) {
			return map;
		}
		final MobileUserVO mobileUserVO = new MobileUserVO();
		mobileUserVO.setMobile(userAndCaptchas.getOrgianlMobile());

		boolean b = mobileUserWriteManage.hasAvailableCode(mobileUserVO);
		logger.info("校验是否存在校验过的验证码结果：" + b);

		final Long userId = SessionContainer.getUserId();
		userAndCaptchas.setId(userId);
		/*
		 * final Long companyId = SessionContainer.getCompanyId();
		 * userAndCaptchas.setCompanyId(companyId);
		 */
		if (b) {

			final HashMap<String, Object> result = mobileUserWriteManage
					.isRepeatPhone(userAndCaptchas);

			logger.info("校验手机号码是否已经被注册结果：" + JSON.toJSONString(result));

			if (!"0".equals(result.get("code").toString())) {
				return result;
			}
			mobileUserWriteManage.modifyMobileWithTx(userAndCaptchas);

			// 发送用户绑定手机变更消息到分销系统
			try {
				mobileUserWriteManage.sendMsg(userAndCaptchas.getMobile(),
						userId.toString());
			} catch (Exception e) {
				logger.error("发送用户绑定手机变更消息到分销系统异常：", e);
			}

			resultMap.put("code", "0");
			resultMap.put("message", "修改成功");

			logger.info("修改绑定手机号码成功");

			// 清除缓存
			String ut = SsoClientUtil.getUt(req);
			SsoClientUtil.logout(ut);

			return resultMap;
		}
		resultMap.put("code", "-1");
		resultMap.put("message", "原手机未校验未校验");
		return resultMap;
	}

	@RequestMapping(value = "/checkMobileAndModifyMobile", method = RequestMethod.POST)
	@ResponseBody
	public Object checkMobileAndModifyMobile(
			@RequestBody MobileUserVO userAndCaptchas, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		return checkMobileAndModifyMobileForm(userAndCaptchas, req, resp);
	}

	/**
	 * 校验图形验证码
	 */
	@RequestMapping(value = "/checkPic", method = RequestMethod.POST)
	@ResponseBody
	public Object checkPic(@RequestBody MobileUserVO user,
			HttpServletRequest req) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 是否有验证码
		if (user.isHasCheckImage()) {
			// 判断图形验证码是否正确
			if (user.getCheckImageCode() == null
					|| !user.getCheckImageCode().equalsIgnoreCase(
							req.getSession()
									.getAttribute(user.getCodeNmInSession())
									.toString())) {
				map.put("code", "-2");
				map.put("message", "验证码输入有误");
				return map;
			}
		}
		map.put("code", "0");
		map.put("message", "验证成功");
		return map;
	}

	/**
	 * 根据ut获取user的id
	 * 
	 * @param ut
	 * @return Long
	 */
	private Long getUserId(String ut) {
		Long userId = null;
		try {
			userId = SessionContainer.getUserId();
		} catch (NullPointerException e) {
			try {
				userId = SsoClientUtil.getUser(ut).getId();
			} catch (NullPointerException e1) {
				userId = null;
			}
		}
		return userId;
	}

	private Map<String, Object> resultMap(int code, String message) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", code);
		result.put("message", message);
		return result;
	}

}
