package com.people2000.user.web.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.MailUtils;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.utils.UpdateCacheUtil;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.business.write.manage.UserWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserDto;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.web.BaseAction;
import com.people2000.user.web.vo.UserVo;

@Controller
@RequestMapping(value = "/userManage")
public class ManageUserAction extends BaseAction {

	@Resource(name = "userWriteManage")
	private UserWriteManage userWriteManage;

	@Resource(name = "mobileUserWriteManage")
	private MobileUserWriteManage mobileUserWriteManage;

	@Resource(name = "userCookieWriteManage")
	private UserCookieWriteManage userCookieWriteManage;

	private static final Logger logger = LogUtils
			.getLogger(ManageUserAction.class);

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getUser(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		User uuser = userWriteManage.getUserById(userId);

		UserVo userVo = new UserVo(uuser);
		if (null != userVo.getMobile()) {
			String mobile = userVo.getMobile() + "";
			if (mobile.length() > 8) {
				String mobile2 = mobile.substring(0, 3) + "******"
						+ mobile.substring(8);
				userVo.setMobile2(mobile2);
			}

		}
		if (StringUtils.isNotEmpty(userVo.getEmail())) {
			String email = userVo.getEmail();
			if (email.indexOf("@") >= 5) {
				String email2 = email.substring(0, email.indexOf("@") - 5)
						+ "*****" + email.substring(email.indexOf("@"));
				userVo.setEmail(email2);
			}
		}

		return success(userVo);
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午12:27:44
	 * @todo 发送邮件code
	 */
	@RequestMapping(value = "/sendEmailCode", method = RequestMethod.POST)
	@ResponseBody
	public Object sendEmailCode(@RequestBody UserDto userDto,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		User uuser = userWriteManage.getUserById(userId);
		if (StringUtils.isNotEmpty(uuser.getEmail())) {
			if (!uuser.getEmail().equals(userDto.getEmail())) {
				return success(-1);
			}
			/*
			 * String chars = "abcdefghijklmnopqrstuvwxyz0123456789";//字母和数字组合
			 * StringBuilder emailCode=new StringBuilder(); for(int
			 * i=0;i<6;i++){//生成6位随机密码
			 * emailCode.append(chars.charAt((int)(Math.random() * 36))); }
			 */
			String chars = "0123456789";// 纯数字
			StringBuilder emailCode = new StringBuilder();
			for (int i = 0; i < 6; i++) {// 生成6位随机密码
				emailCode.append(chars.charAt((int) (Math.random() * 10)));
			}
			try {
				MailUtils.sendMail(userDto.getEmail(), "邮箱修改通知", "您的邮箱修改验证码为 "
						+ emailCode.toString());
				String ut = SsoClientUtil.getUt(req);
				// 放入缓存
				String code = emailCode.toString();
				CacheCommUtils.putCache(ut + "_emailCode", code);
			} catch (Exception e) {
				LogUtils.getLogger(getClass()).error(
						"发送提醒邮件失败" + e.getMessage());
			}
		}
		return success(0);
	}

	/**
	 * 
	 * <p>
	 * 发送邮箱验证码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月3日 上午11:13:17
	 * @param userDto
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/sendEmailCaptchas", method = RequestMethod.POST)
	@ResponseBody
	public Object sendEmailCaptchas(@RequestBody UserDto userDto,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		/*
		 * String chars = "abcdefghijklmnopqrstuvwxyz0123456789";//字母和数字组合
		 * StringBuilder emailCode=new StringBuilder(); for(int
		 * i=0;i<6;i++){//生成6位随机密码
		 * emailCode.append(chars.charAt((int)(Math.random() * 36))); }
		 */
		String chars = "0123456789";// 纯数字
		StringBuilder emailCode = new StringBuilder();
		for (int i = 0; i < 6; i++) {// 生成6位随机密码
			emailCode.append(chars.charAt((int) (Math.random() * 10)));
		}
		try {
			MailUtils.sendMail(userDto.getEmail(), "【医学园区】您正在申请绑定当前邮箱",
					"您的验证码为： " + emailCode.toString());
			String ut = SsoClientUtil.getUt(req);
			// 放入缓存
			String code = emailCode.toString();
			CacheCommUtils.putCache(ut + "_emailCode", code);
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error("发送提醒邮件失败" + e.getMessage());
		}
		return success(0);
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午12:29:28
	 * @todo 保存修改的邮箱
	 */
	@RequestMapping(value = "/saveEditEmail", method = RequestMethod.POST)
	@ResponseBody
	public Object saveEditEmail(@RequestBody UserDto userDto,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		// User uuser = userWriteManage.getUserById(userId);
		if (StringUtils.isNotEmpty(userDto.getEmailCode())) {
			String ut = SsoClientUtil.getUt(req);
			Object codeObj = CacheCommUtils.getCache(ut + "_emailCode");
			if (codeObj == null) {
				return fail("验证码错误");
			}

			String code = codeObj.toString();
			if (!userDto.getEmailCode().equals(code)) {
				return fail("验证码错误");
			}
		} else {
			return fail("请输入验证码!");
		}
		// 验证邮箱是否重复
		if (StringUtils.isNotEmpty(userDto.getEmail2())) {
			User user = new User();
			user.setEmail(userDto.getEmail2());
			List<User> list = userWriteManage.getUserByEmail(user);
			if (list.size() > 0) {
				return fail("该邮箱已经被绑定，请更换!");
			}
		}
		UserDto dto = new UserDto();
		dto.setId(userId);
		dto.setEmail(userDto.getEmail2());
		userWriteManage.updateUserWithTx(dto);
		return success(0);
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午12:27:44
	 * @todo
	 */
	@RequestMapping(value = "/sendMobileCaptchas", method = RequestMethod.POST)
	@ResponseBody
	public Object sendMobileCaptchas(@RequestBody UserDto userDto,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		User uuser = userWriteManage.getUserById(userId);
		MobileUserVO captchas = new MobileUserVO();
		if (StringUtils.isNotEmpty(uuser.getMobile())) {
			captchas.setMobile(uuser.getMobile());
			resultMap = mobileUserWriteManage.sendCodeWithTx(captchas);
		} else {
			resultMap.put("code", 5);
			resultMap.put("message", "手机号码不能为空!");
		}

		return resultMap;
	}

	/**
	 * 
	 * @author xuc
	 * @time 下午12:29:28
	 * @todo
	 */
	@RequestMapping(value = "/checkMobileCaptchas", method = RequestMethod.POST)
	@ResponseBody
	public Object saveEditMobile(@RequestBody MobileUserVO vo,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		User uuser = userWriteManage.getUserById(userId);

		if (StringUtils.isEmpty(uuser.getMobile())) {// 用户mobile为空,不执行校验逻辑
			MobileUserVO mobileUserVO = new MobileUserVO();
			mobileUserVO.setId(userId);
			mobileUserVO.setNewMobile(vo.getMobile());
			List<UserCookie> usercookies = new ArrayList<UserCookie>();
			UserCache userCache = new UserCache();
			try {
				usercookies = userCookieWriteManage
						.getUserCookiesByIdWithTx(mobileUserVO.getId());
				if (usercookies != null && usercookies.size() > 0) {
					UserCookie userCookie = usercookies.get(0);
					String ut = userCookie.getCookieValue();
					userCache = SsoClientUtil.getUser(ut);
					if (ut != null && !"".equals(ut)) {
						if (userCache == null) {
							// 从表中取用户
							/*
							 * UserLoginServiceClient userLoginServiceClient =
							 * UserLoginServiceClient.getInstanst(); try {
							 * userCache =
							 * userLoginServiceClient.getUserByLoginCookie(ut);
							 * } catch (Exception e) {
							 * //LogUtils.getLogger(getClass
							 * ()).error(e.getMessage(),e);;
							 * logger.error(e.getMessage()); return
							 * fail("查询出错"); }
							 */
						}
					}
				}
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
			}

			boolean d = false;
			try {
				d = mobileUserWriteManage.isRepeatMobile(
						mobileUserVO.getNewMobile(),
						mobileUserVO.getCompanyId());
			} catch (SQLException e) {
				// LogUtils.getLogger(getClass()).error(e.getMessage(),e);;
				logger.error(e.getMessage());
				return fail("查询出错");
			}
			if (d) {
				return fail("该新手机号已经绑定其他账号");
			}

			try {
				User user = new User();
				user.setId(mobileUserVO.getId());
				user.setMobile(mobileUserVO.getNewMobile());
				mobileUserWriteManage.updateByIdWithTx(user);
				if (userCache != null) {
					userCache.setMobile(user.getMobile());
					UpdateCacheUtil.updateCache(usercookies, userCache);
				}
			} catch (SQLException e) {
				// LogUtils.getLogger(getClass()).error(e.getMessage(),e);;
				logger.error(e.getMessage());
				return fail("查询出错");
			}

		} else {
			MobileUserVO mobileUserVO = new MobileUserVO();

			mobileUserVO.setId(userId);
			mobileUserVO.setCaptchas(vo.getCaptchas());
			mobileUserVO.setMobile(uuser.getMobile());
			mobileUserVO.setNewMobile(vo.getMobile());

			HashMap<String, Object> map = new HashMap<String, Object>();
			User user = new User();
			List<UserCookie> usercookies = new ArrayList<UserCookie>();
			UserCache userCache = new UserCache();

			user.setId(mobileUserVO.getId());
			if (!ConstantUser.operator_type.operator.equals(mobileUserVO
					.getOperatorType())) {// 如果不是运营人员，则需要短信校验
				try {
					map = mobileUserWriteManage.checkCodeWithTx(mobileUserVO);
				} catch (Exception e) {
					// LogUtils.getLogger(getClass()).error(e.getMessage(),e);;
					logger.error(e.getMessage());
					return fail("查询出错");
				}
			} else {// 运营人员默认短信校验成功
				map.put("code", 0);
			}

			try {
				usercookies = userCookieWriteManage
						.getUserCookiesByIdWithTx(mobileUserVO.getId());
				if (usercookies != null && usercookies.size() > 0) {
					UserCookie userCookie = usercookies.get(0);
					String ut = userCookie.getCookieValue();
					userCache = SsoClientUtil.getUser(ut);
					if (ut != null && !"".equals(ut)) {
						if (userCache == null) {
							// 从表中取用户
							/*
							 * UserLoginServiceClient userLoginServiceClient =
							 * UserLoginServiceClient.getInstanst(); try {
							 * userCache =
							 * userLoginServiceClient.getUserByLoginCookie(ut);
							 * } catch (Exception e) {
							 * //LogUtils.getLogger(getClass
							 * ()).error(e.getMessage(),e);;
							 * logger.error(e.getMessage()); return
							 * fail("查询出错"); }
							 */
						}
					}
				}
			} catch (SQLException e) {
				LogUtils.getLogger(getClass()).error(e.getMessage(), e);
				;
			}
			if ("0".equals(map.get("code").toString())) {
				Boolean b2 = null;
				try {
					b2 = mobileUserWriteManage.hasMobile(mobileUserVO);
				} catch (SQLException e) {
					// LogUtils.getLogger(getClass()).error(e.getMessage(),e);;
					logger.error(e.getMessage());
					return fail("查询出错");
				}
				if (!b2) {
					return fail("用户和绑定手机号码不匹配");
				}

				if (mobileUserVO.getNewMobile() == null
						|| "".equals(mobileUserVO.getNewMobile())) {
					return fail("需要新手机号才能进行解绑");
				} else {
					if (mobileUserVO.getMobile() != null
							&& mobileUserVO.getMobile().equals(
									mobileUserVO.getNewMobile())) {
						return fail("换绑手机号必须与当前手机号不一致");
					}
					// 判断该手机号码是否已经绑定其他用户
					boolean d = false;
					try {
						d = mobileUserWriteManage.isRepeatMobile(
								mobileUserVO.getNewMobile(),
								mobileUserVO.getCompanyId());
					} catch (SQLException e) {
						// LogUtils.getLogger(getClass()).error(e.getMessage(),e);;
						logger.error(e.getMessage());
						return fail("查询出错");
					}
					if (d) {
						return fail("该新手机号已经绑定其他账号");
					}
					user.setMobile(mobileUserVO.getNewMobile());
				}

				try {
					mobileUserWriteManage.updateByIdWithTx(user);
					if (userCache != null) {
						userCache.setMobile(user.getMobile());
						UpdateCacheUtil.updateCache(usercookies, userCache);
					}
				} catch (SQLException e) {
					// LogUtils.getLogger(getClass()).error(e.getMessage(),e);;
					logger.error(e.getMessage());
					return fail("查询出错");
				}

			} else {
				return fail("验证码错误");
			}
		}

		return success("操作成功");
	}

}
