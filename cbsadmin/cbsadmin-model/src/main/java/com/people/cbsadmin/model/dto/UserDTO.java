package com.people.cbsadmin.model.dto;

import java.math.BigDecimal;

import com.people.cbsadmin.model.po.User;



/**
 * 
 * 业务实体类
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017年8月19日 下午1:06:47,content:
 * </p>
 * 
 * @author dusai
 * @date 2017年8月19日 下午1:06:47
 * @since
 * @version
 */
public class UserDTO extends User {

	/**
	 */
	private static final long serialVersionUID = -1773927873819827705L;

	/**
	 * 账户信息
	 */
	private Long accountId;// 账户id
	private Integer accountType;// 账户类型
	private String payPwd;// 支付密码
	private BigDecimal amount;// 账户可用金额
	private BigDecimal freezingAmount;// 账户冻结金额
	private String pSalt;// 支付密码盐值

	/**
	 * 等级信息
	 */
	private Long gradeId;// 等级id
	private Integer gradeType; // 等级类型
	private String gradeName; // 等级名称
	private String iconUrl;// 等级icon
	private Integer type;// 4会员 5企业用户

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFreezingAmount() {
		return freezingAmount;
	}

	public void setFreezingAmount(BigDecimal freezingAmount) {
		this.freezingAmount = freezingAmount;
	}

	public String getpSalt() {
		return pSalt;
	}

	public void setpSalt(String pSalt) {
		this.pSalt = pSalt;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
