package com.people2000.user.model.dto;

import java.math.BigDecimal;

import com.people2000.user.model.constant.ConstantUser;

public class AccountPayDTO {
	private Long accountId;// 账户id
	private Long userId; // 用户id
	private BigDecimal amount;// 支付金额
	private String payPwd;// 支付密码
	private Integer mode = ConstantUser.pay_mode.canNotMinus;// 10017001
																// 允许账户余额不足时进行支付
																// 10017002允许

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

}
