/**
 *
 */
package com.people2000.user.web.action;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.utils.CacheCommUtils;
import com.people2000.user.business.utils.SsoClientUtil;
import com.people2000.user.business.write.manage.LoginWriteManage;
import com.people2000.user.business.write.manage.UnionLoginManage;
import com.people2000.user.business.write.manage.UserCookieWriteManage;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserUnionLoginDTO;
import com.people2000.user.model.po.ibatis.UserCookie;
import com.people2000.user.model.vo.MobileUserVO;
import com.people2000.user.web.BaseAction;

/**
 * @author dailf
 * @time 2015-08-03
 * @description <pre>
 * 联合登录
 * </pre>
 *
 */
@Controller
@RequestMapping(value = "/unionLogin")
public class UnionLoginAction extends BaseAction {

	@Resource(name = "loginWriteManageImpl")
	private LoginWriteManage loginWriteManage;

	@Resource(name = "userCookieWriteManage")
	private UserCookieWriteManage userCookieWriteManage;

	@Resource(name = "unionLoginManage")
	private UnionLoginManage unionLoginManage;

	// protected CacheProxy cache;

	/**
	 * 联合登录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object unionLogin(UserUnionLoginDTO userUnionLoginDTO,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 获取session
			userUnionLoginDTO.setSessionId(req.getSession().getId());
			// 跨域调用验证
			unionLoginManage.stateValidate(userUnionLoginDTO);
			// 处理登录数据
			UserUnionLoginDTO dto = (UserUnionLoginDTO) unionLoginManage
					.savaUnionUserInfoWithTx(userUnionLoginDTO);
			// 如果用户是第一次登入，则生成一个和openId关联的ut
			UserCache cacheUser = null;
			if (null == dto.getUserId()) {
				cacheUser = new UserCache();
			} else {
				cacheUser = unionLoginManage.getUserCache(dto.getUserId());
			}
			// 写缓存 cookie
			UserCookie userCookie = SsoClientUtil.login(cacheUser, req, resp);
			// 保存登录日志
			// 写登陆日志
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("code", "0");
			map.put("data", cacheUser);
			map.put("ut", userCookie.getCookieValue());

			if (!StringUtil.isBlank(userUnionLoginDTO.getState())) {
				String[] strSplit = userUnionLoginDTO.getState().split("_");
				if (strSplit.length < 3) {
					map.put("redirectUrl", "");
				} else {
					map.put("redirectUrl",
							userUnionLoginDTO.getState().split("_")[2]);
				}
			}
			try {
				// 如果有用户ID则保存登录日志
				// 否则保存cookie
				if (null == dto.getUserId()) {
					SsoClientUtil.setUnionOpenId(userUnionLoginDTO,
							userCookie.getCookieValue());
				} else {
					Long userLoginId = loginWriteManage.insertLoginLogWithTx(
							map, req);
					userCookie.setUserLoginId(userLoginId);
					userCookie.setCompanyId(cacheUser.getCompanyId());
					userCookieWriteManage.saveUserCookieWithTx(userCookie);
				}
			} catch (Exception e) {
				LogUtils.getLogger(getClass()).error(e.getMessage());
			}
			return map;
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).info(e.getMessage());
			return fail(e.getMessage());
		}
	}

	/**
	 * 获取参数appid，redirect_uri，state
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRelatedParams", method = RequestMethod.POST)
	@ResponseBody
	public Object getRelatedParams(
			@RequestBody UserUnionLoginDTO userUnionLoginDTO,
			HttpServletRequest req) {
		try {
			Integer gateway = userUnionLoginDTO.getGateway();
			LogUtils.getLogger(getClass()).info("开始获取预登陆参数：gateway=" + gateway);

			// 替换成ut

			String flag = "unionId" + UUID.randomUUID();

			CacheCommUtils.putCache(flag, flag, 5);
			String state = flag + "_" + userUnionLoginDTO.getGateway() + "_"
					+ userUnionLoginDTO.getRedirectUrl();
			LogUtils.getLogger(getClass()).info("state:" + state);
			userUnionLoginDTO.setState(state);
			// 获取联合登录相关参数
			String returnUrl = unionLoginManage
					.getRelatedParams(userUnionLoginDTO);
			LogUtils.getLogger(getClass()).info("returnUrl:" + returnUrl);
			return success(returnUrl);
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage());
			return fail(e.getMessage());
		}
	}

	/**
	 * 用户绑定密码
	 * 
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bindUserPassword", method = RequestMethod.POST)
	@ResponseBody
	public Object bindUserPassword(MobileUserVO userVo) {
		boolean flag = false;
		try {
			flag = unionLoginManage.bindUserPasswordWithTx(userVo);
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage());
		}
		if (flag) {
			return success("");
		} else {
			return fail("");
		}
	}

	/**
	 * 检查密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	@ResponseBody
	public Object checkPassword() {
		boolean flag = false;
		try {
			flag = unionLoginManage.checkPassword();
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage());
			return fail(e.getMessage());
		}
		if (flag) {
			// 可以进行密码设置
			return success("0");
		} else {
			// 已经设置过密码了
			return success("-1");
		}
	}

	/**
	 * 是否显示图标
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/iconInvisible", method = RequestMethod.GET)
	@ResponseBody
	public Object iconInvisible(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Map<Integer, Integer> returnMap = unionLoginManage.iconInvisible();
			return success(returnMap);
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage());
			return fail(e.getMessage());
		}
	}

}
