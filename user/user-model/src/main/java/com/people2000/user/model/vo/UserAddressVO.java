package com.people2000.user.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserAddressVO implements Serializable {
	private Long id;// 收货地址id
	private String userName;// 用户名
	private Long provinceId;// 省份id
	private Long provinceCode;// 省份code
	private String provinceName;// 省份名
	private Long cityId;// 城市id
	private Integer cityCode;// 城市code
	private String cityName;// 城市name
	private Long regionId;// 区域id
	private String regionName;// 区域名
	private String detailAddress;// 详细地址
	private String mobile;// 手机号码
	private Integer defaultIs;// 是否默认
	private Long userId;// 用户id
	private Integer sex;// 性别
	private BigDecimal longitude;// 经度
	private BigDecimal latitude;// 纬度
	private String exactAddress;// 定位地址
	private Long companyId;// 公司id
	private Date createTime;// 创建时间
	private String identityCardNumber;// 身份证号
	private String realName;
	private Integer certification;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Long provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getDefaultIs() {
		return defaultIs;
	}

	public void setDefaultIs(Integer defaultIs) {
		this.defaultIs = defaultIs;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getExactAddress() {
		return exactAddress;
	}

	public void setExactAddress(String exactAddress) {
		this.exactAddress = exactAddress;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getCityCode() {
		return cityCode;
	}

	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public Integer getCertification() {
		// FIXME: 2016/9/28 收货地址 实名认证缺少审核流程. 暂时不审核.
		if (realName == null || "".equals(realName)
				|| identityCardNumber == null || "".equals(identityCardNumber)) {
			return 0;
		} else {
			return 1;
		}
	}

	public void setCertification(Integer certification) {
		this.certification = certification;
	}
}