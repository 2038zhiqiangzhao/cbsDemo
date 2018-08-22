package com.people2000.user.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserGradeVO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 4007102116029015741L;
	private Long id;

	private String gradeName;// 等级名称
	private Integer gradeType;// 等级编码
	private Integer groupType;// 等级分类
	private String iconUrl;// 等级icon
	private BigDecimal price;// 等级价格
	private BigDecimal discount;// 等级享受折扣

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

}
