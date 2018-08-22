package com.people2000.user.business.write.manage;

import java.sql.SQLException;

import com.people2000.common.base.bean.ResultObjct;
import com.people2000.user.model.dto.AccountPayDTO;
import com.people2000.user.model.dto.AccountRechargeDTO;
import com.people2000.user.model.dto.UserAccountDTO;
import com.people2000.user.model.dto.UserPayPasswordChangeDTO;
import com.people2000.user.model.exception.OuserMangeException;
import com.people2000.user.model.po.ibatis.UserAccountPO;

public interface UserAccountWriteManage {

	/**
	 * 
	 * <p>
	 * 更新支付密码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月16日 下午7:29:33
	 * @param userAccountDTO
	 * @throws OuserMangeException
	 * @see
	 */
	void updatePayPasswordWithTx(UserPayPasswordChangeDTO pwdChangeDTO)
			throws OuserMangeException;

	/**
	 * 
	 * <p>
	 * 验证支付密码有效性
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月16日 下午7:29:33
	 * @param userAccountDTO
	 * @throws OuserMangeException
	 * @see
	 */
	ResultObjct validatePayPassword(UserAccountDTO userAccountDTO)
			throws OuserMangeException;

	UserAccountPO queryUserAccountByUserId(Long userId) throws SQLException;

	void addUserAccountWithTx(UserAccountPO userAccountPO)
			throws OuserMangeException;

	void createUserAccountWithTx(UserAccountDTO accountDTO)
			throws OuserMangeException;

	void payAccountWithTx(AccountPayDTO accountPayDTO)
			throws OuserMangeException;

	void rechargeAccountWithTx(AccountRechargeDTO rechargeDTO)
			throws OuserMangeException;

}
