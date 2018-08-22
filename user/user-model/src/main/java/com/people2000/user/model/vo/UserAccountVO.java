package com.people2000.user.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserAccountVO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -1458509496180861506L;

	private Long id;

	private Long userId;

	private BigDecimal amount;

	private BigDecimal freezingAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
