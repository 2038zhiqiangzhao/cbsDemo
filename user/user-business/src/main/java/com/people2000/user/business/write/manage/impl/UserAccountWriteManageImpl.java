package com.people2000.user.business.write.manage.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.people2000.common.base.bean.ResultObjct;
import com.people2000.user.business.utils.PasswordUtil;
import com.people2000.user.business.utils.SaltUtils;
import com.people2000.user.business.write.dao.AccountRecordDAOWrite;
import com.people2000.user.business.write.dao.UserAccountDAOWrite;
import com.people2000.user.business.write.dao.ext.UserDAOWrite2;
import com.people2000.user.business.write.dao.ext.UserGradeDAOWrite2;
import com.people2000.user.business.write.manage.MobileUserWriteManage;
import com.people2000.user.business.write.manage.UserAccountWriteManage;
import com.people2000.user.model.constant.ConstantUser;
import com.people2000.user.model.constant.ErrorCode;
import com.people2000.user.model.dto.AccountPayDTO;
import com.people2000.user.model.dto.AccountRechargeDTO;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.dto.UserGradeInputDTO;
import com.people2000.user.model.dto.UserPayPasswordChangeDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.AccountRecordPO;
import com.people2000.user.model.po.ibatis.User;
import com.people2000.user.model.po.ibatis.UserAccountPO;
import com.people2000.user.model.po.ibatis.UserAccountPOExample;
import com.people2000.user.model.po.ibatis.UserGradePO;
import com.people2000.user.model.vo.MobileUserVO;

@Service("userAccountWriteManage")
public class UserAccountWriteManageImpl implements UserAccountWriteManage {

	@Resource
	private UserAccountDAOWrite userAccountDAOWrite;
	@Resource
	private MobileUserWriteManage mobileUserWriteManage;
	@Resource
	private AccountRecordDAOWrite accountRecordDAOWrite;
	@Resource(name = "userGradeDAOWrite")
	private UserGradeDAOWrite2 userGradeDAOWrite;
	@Resource(name = "userDAOWrite")
	private UserDAOWrite2 userDAOWrite;

	/**
	 * 
	 * <p>
	 * 更新支付密码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月16日 下午7:31:40
	 * @param userAccountDTO
	 * @throws OuserMangeException
	 * @see com.people2000.user.business.write.manage.UserAccountWriteManage#updatePayPassword(com.people2000.user.model.dto.UserAccountDTO)
	 */
	@Override
	public void updatePayPasswordWithTx(UserPayPasswordChangeDTO pwdChangeDTO)
			throws OuserMangeException {
		// 1检查验证码是否验证
		MobileUserVO mobileUserVO = new MobileUserVO();
		mobileUserVO.setMobile(pwdChangeDTO.getMobile());
		boolean codeValidate = mobileUserWriteManage
				.hasAvailableCode(mobileUserVO);
		if (!codeValidate) {
			throw new OuserMangeException(ErrorCode.system__error, "未验证过验证码");
		}
		try {
			// 2 判断旧密码是否和新密码相同
			UserAccountPO accountPO = queryUserAccountByUserId(pwdChangeDTO
					.getUserId());
			if (accountPO.getPayPwd() != null && accountPO.getpSalt() != null) {
				String oldPassword = PasswordUtil.getSecretPassWord(
						pwdChangeDTO.getNewPassword(), accountPO.getpSalt(), 1);
				if (oldPassword.equals(accountPO.getPayPwd())) {
					throw new OuserMangeException(ErrorCode.bussiness_error,
							"新密码不能和旧密码相同");
				}
			}

			// 3.获取新盐值和新加密密码
			String newSalt = SaltUtils.getRandomSalt(10);
			String newPassword = PasswordUtil.getSecretPassWord(
					pwdChangeDTO.getNewPassword(), newSalt, 1);

			// 4. 更新数据
			UserAccountPO userAccountPO = new UserAccountPO();
			userAccountPO.setId(accountPO.getId());
			userAccountPO.setPayPwd(newPassword);
			userAccountPO.setpSalt(newSalt);
			userAccountDAOWrite.updateByPrimaryKeySelective(userAccountPO);
		} catch (SQLException e) {
			throw new OuserMangeException(ErrorCode.sql_error, e);
		}

	}

	/**
	 * 
	 * <p>
	 * 验证支付密码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月16日 下午7:32:07
	 * @param userAccountDTO
	 * @return
	 * @throws OuserMangeException
	 * @see com.people2000.user.business.write.manage.UserAccountWriteManage#validatePayPassword(com.people2000.user.model.dto.UserAccountDTO)
	 */
	@Override
	public ResultObjct validatePayPassword(UserAccountDTO userAccountDTO)
			throws OuserMangeException {
		try {
			UserAccountPO accountPO = queryUserAccountByUserId(userAccountDTO
					.getUserId());
			if (StringUtils.isEmpty(accountPO.getPayPwd())) {
				return ResultObjct.returnFail("未设置支付密码");
			}
			String password = PasswordUtil.getSecretPassWord(
					userAccountDTO.getPayPwd(), accountPO.getpSalt(), 1);
			if (!password.equals(accountPO.getPayPwd())) {
				return ResultObjct.returnFail("支付密码验证不通过");
			}
		} catch (SQLException e) {
			throw new OuserMangeException(ErrorCode.sql_error, e);
		}
		return ResultObjct.returnSuccess("支付密码验证通过");
	}

	/**
	 * 
	 * <p>
	 * 根据用户id查询余额账户信息
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午12:44:02
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @see com.people2000.user.business.write.manage.UserAccountWriteManage#queryUserAccountByUserId(java.lang.Long)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserAccountPO queryUserAccountByUserId(Long userId)
			throws SQLException {
		UserAccountPO result = null;
		UserAccountPOExample example = new UserAccountPOExample();
		UserAccountPOExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		example.setLimitClauseStart(0L);
		example.setLimitClauseCount(1L);

		List<UserAccountPO> list = userAccountDAOWrite.selectByExample(example,
				false);
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * 添加余额账户
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午12:43:35
	 * @param userAccountPO
	 * @throws OuserMangeException
	 * @see com.people2000.user.business.write.manage.UserAccountWriteManage#addUserAccountWithTx(com.people2000.user.model.po.ibatis.UserAccountPO)
	 */
	@Override
	public void addUserAccountWithTx(UserAccountPO userAccountPO)
			throws OuserMangeException {
		if (userAccountPO.getUserId() == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"user id is null");
		}
		try {
			userAccountDAOWrite.insert(userAccountPO);
		} catch (SQLException e) {
			throw new OuserMangeException(ErrorCode.sql_error, e);
		}
	}

	/**
	 * 
	 * <p>
	 * 支付
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午12:43:22
	 * @param accountPayDTO
	 * @throws OuserMangeException
	 * @see com.people2000.user.business.write.manage.UserAccountWriteManage#payAccountWithTx(com.people2000.user.model.dto.AccountPayDTO)
	 */
	@Override
	public void payAccountWithTx(AccountPayDTO accountPayDTO)
			throws OuserMangeException {
		if (accountPayDTO.getUserId() == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"user id is null");
		}
		if (accountPayDTO.getAmount() == null
				|| accountPayDTO.getAmount().doubleValue() <= 0.0d) {
			throw new OuserMangeException(ErrorCode.input_null, "支付金额必须大于0");
		}

		// 验证支付密码
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setUserId(accountPayDTO.getUserId());
		userAccountDTO.setPayPwd(accountPayDTO.getPayPwd());
		ResultObjct resultObjct = validatePayPassword(userAccountDTO);
		if (!"0".equals(resultObjct.getCode())) {
			throw new OuserMangeException(ErrorCode.bussiness_error,
					resultObjct.getMessage());
		}

		try {
			// 获取余额
			UserAccountPO accountPO = queryUserAccountByUserId(accountPayDTO
					.getUserId());
			BigDecimal restAmount = accountPO.getAmount();
			if (restAmount == null) {
				restAmount = new BigDecimal(0d);
			}

			// 获取扣减后的金额
			BigDecimal subAmount = restAmount.subtract(accountPayDTO
					.getAmount());
			if (subAmount.doubleValue() < 0d) {// 判断是否余额不足
				if (accountPayDTO.getMode().intValue() != ConstantUser.pay_mode.canMinus) {// 判断是否允许余额不足
					throw new OuserMangeException(ErrorCode.bussiness_error,
							"余额不足，请充值！");
				}
			}

			// 更新余额
			UserAccountPO newAccountPO = new UserAccountPO();
			newAccountPO.setId(accountPO.getId());
			newAccountPO.setAmount(subAmount);
			userAccountDAOWrite.updateByPrimaryKeySelective(newAccountPO);

			// 插入账户变更记录
			AccountRecordPO accountRecordPO = new AccountRecordPO();
			accountRecordPO.setAccountId(accountPO.getId());
			accountRecordPO.setOptAmount(accountPayDTO.getAmount());
			accountRecordPO.setOptType(ConstantUser.account_opt_type.pay);
			accountRecordPO.setOptCommont("支付");
			accountRecordPO.setRestAmount(subAmount);
			accountRecordPO.setIsAvailable(1);

			accountRecordDAOWrite.insert(accountRecordPO);

		} catch (SQLException e) {
			throw new OuserMangeException(ErrorCode.sql_error, e);
		}

	}

	/**
	 * 
	 * <p>
	 * 账户充值
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午1:19:30
	 * @param userAccountDTO
	 * @throws OuserMangeException
	 * @see com.people2000.user.business.write.manage.UserAccountWriteManage#rechargeAccountWithTx(com.people2000.user.model.dto.UserAccountDTO)
	 */
	@Override
	public void rechargeAccountWithTx(AccountRechargeDTO rechargeDTO)
			throws OuserMangeException {
		if (rechargeDTO.getUserId() == null) {
			throw new OuserMangeException(ErrorCode.input_null,
					"user id is null");
		}
		if (rechargeDTO.getAmount() == null
				|| rechargeDTO.getAmount().doubleValue() <= 0.0d) {
			throw new OuserMangeException(ErrorCode.input_null, "充值金额必须大于0");
		}
		try {
			// 获取余额
			UserAccountPO accountPO = queryUserAccountByUserId(rechargeDTO
					.getUserId());
			BigDecimal restAmount = accountPO.getAmount();
			if (restAmount == null) {
				restAmount = new BigDecimal(0d);
			}

			// 充值后的金额
			BigDecimal addedAmount = restAmount.add(rechargeDTO.getAmount());

			// 更新余额
			UserAccountPO newAccountPO = new UserAccountPO();
			newAccountPO.setId(accountPO.getId());
			newAccountPO.setAmount(addedAmount);
			userAccountDAOWrite.updateByPrimaryKeySelective(newAccountPO);

			// 插入账户变更记录
			AccountRecordPO accountRecordPO = new AccountRecordPO();
			accountRecordPO.setAccountId(accountPO.getId());
			accountRecordPO.setOptAmount(rechargeDTO.getAmount());
			accountRecordPO.setOptType(ConstantUser.account_opt_type.recharge);
			accountRecordPO
					.setOptCommont(rechargeDTO.getOptCommont() == null ? "充值"
							: rechargeDTO.getOptCommont());
			accountRecordPO.setRestAmount(addedAmount);

			accountRecordDAOWrite.insert(accountRecordPO);

			// 判断能否升等级.只有会员才升级
			User user = userDAOWrite.selectByPrimaryKey(
					rechargeDTO.getUserId(), false);
			if (user.getType().intValue() == ConstantUser.user_type.member) {
				// 根据充值后剩余金额,查询对应等级
				UserGradeInputDTO gradeInputDTO = new UserGradeInputDTO();
				gradeInputDTO.setPrice(addedAmount);
				UserGradePO grade = userGradeDAOWrite
						.selectUserGradeByPrice(gradeInputDTO);

				// 升用户等级
				if (grade != null) {
					User updateUser = new User();
					updateUser.setId(rechargeDTO.getUserId());
					updateUser.setGradeId(grade.getId());
					userDAOWrite.updateByPrimaryKeySelective(updateUser);
				}

			}
		} catch (SQLException e) {
			throw new OuserMangeException(ErrorCode.sql_error, e);
		}

	}

	/**
	 * 
	 * <p>
	 * 给用户开设初始化账户
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月17日 下午1:32:58
	 * @param userId
	 * @throws OuserMangeException
	 * @see com.people2000.user.business.write.manage.UserAccountWriteManage#createUserAccountWithTx(java.lang.Long)
	 */
	@Override
	public void createUserAccountWithTx(UserAccountDTO accountDTO)
			throws OuserMangeException {
		UserAccountPO accountPO = new UserAccountPO();
		accountPO.setUserId(accountDTO.getUserId());
		accountPO.setAccountType(accountDTO.getAccountType() == null ? 0
				: accountDTO.getAccountType());
		accountPO.setAmount(new BigDecimal(0d));
		accountPO.setFreezingAmount(new BigDecimal(0d));
		accountPO.setIsAvailable(1);
		addUserAccountWithTx(accountPO);
	}

}
