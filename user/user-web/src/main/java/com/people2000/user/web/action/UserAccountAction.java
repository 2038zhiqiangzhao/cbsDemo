package com.people2000.user.web.action;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.user.business.utils.CommonUtils;
import com.people2000.user.business.utils.SessionContainer;
import com.people2000.user.business.write.manage.UserAccountWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.dto.AccountPayDTO;
import com.people2000.user.model.dto.AccountRechargeDTO;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.dto.UserPayPasswordChangeDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UserAccountPO;
import com.people2000.user.web.BaseAction;

@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountAction extends BaseAction {

	@Resource
	private UserAccountWriteManage userAccountWriteManage;

	/**
	 * 
	 * <p>
	 * 账户支付
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午2:48:09
	 * @param accountPayDTO
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/payAccount")
	@ResponseBody
	public Object payAccount(@RequestBody AccountPayDTO accountPayDTO,
			HttpServletRequest req, HttpServletResponse resp) {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		Integer type = SessionContainer.getSession().getType();
		if (type.intValue() == ConstantUser.user_type.member) {// 会员不支持模式1
			if (accountPayDTO.getMode().intValue() == ConstantUser.pay_mode.canMinus) {
				return failMessage("该用户不支持该支付模式");
			}
		}
		accountPayDTO.setUserId(userId);
		try {
			userAccountWriteManage.payAccountWithTx(accountPayDTO);
		} catch (OuserMangeException e) {
			return failMessage(e.getMessage());
		}
		return success(0);
	}

	/**
	 * 
	 * <p>
	 * 更新支付密码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午2:48:24
	 * @param pwdChangeDTO
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/updatePayPassword")
	@ResponseBody
	public Object updatePayPassword(
			@RequestBody UserPayPasswordChangeDTO pwdChangeDTO,
			HttpServletRequest req, HttpServletResponse resp) {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		pwdChangeDTO.setUserId(userId);
		pwdChangeDTO.setMobile(SessionContainer.getMobile());

		try {
			userAccountWriteManage.updatePayPasswordWithTx(pwdChangeDTO);
		} catch (OuserMangeException e) {
			return failMessage(e.getMessage());
		}
		return success(0);
	}

	/**
	 * 
	 * <p>
	 * 查询是否设置过支付密码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午2:47:48
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/hasPayPassword")
	@ResponseBody
	public Object hasPayPassword(HttpServletRequest req,
			HttpServletResponse resp) {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		try {
			UserAccountPO accountPO = userAccountWriteManage
					.queryUserAccountByUserId(userId);
			return success(accountPO.getPayPwd() != null);
		} catch (SQLException e) {
			return failMessage(e.getMessage());
		}

	}

	/**
	 * 
	 * <p>
	 * 校验支付密码是否正确
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午2:48:43
	 * @param userAccountDTO
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/validatePayPassword")
	@ResponseBody
	public Object validatePayPassword(
			@RequestBody UserAccountDTO userAccountDTO, HttpServletRequest req,
			HttpServletResponse resp) {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		userAccountDTO.setUserId(userId);
		try {
			return userAccountWriteManage.validatePayPassword(userAccountDTO);
		} catch (OuserMangeException e) {
			return failMessage(e.getMessage());
		}
	}

	/**
	 * 
	 * <p>
	 * 账户充值
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午2:49:07
	 * @param userAccountDTO
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @see
	 */
	@RequestMapping(value = "/rechargeAccount")
	@ResponseBody
	public Object rechargeAccount(
			@RequestBody AccountRechargeDTO userAccountDTO,
			HttpServletRequest req, HttpServletResponse resp) {
		// 获取登陆id
		Long userId = CommonUtils.getCurretnOperateId();
		userAccountDTO.setUserId(userId);
		try {
			userAccountWriteManage.rechargeAccountWithTx(userAccountDTO);
		} catch (OuserMangeException e) {
			return fail(e.getMessage());
		}
		return success(0);
	}

	/**
	 * 
	 * <p>
	 * 后台充值
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年9月7日 上午10:33:26
	 * @param userAccountDTO
	 * @param req
	 * @param resp
	 * @return
	 * @see
	 */
	@RequestMapping(value = "/rechargeAccountBack")
	@ResponseBody
	public Object rechargeAccountBack(
			@RequestBody AccountRechargeDTO userAccountDTO,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			userAccountWriteManage.rechargeAccountWithTx(userAccountDTO);
		} catch (OuserMangeException e) {
			return fail(e.getMessage());
		}
		return success(0);
	}
}
