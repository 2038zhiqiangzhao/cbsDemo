package com.people.cbsadmin.model.dto; 

import java.io.Serializable;
import java.util.Date;

import com.people2000.common.base.page.Pagination;





/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年5月14日 下午1:11:38 
 * 类说明 
 */
public class CommunityActivityAdminDto extends Pagination implements Serializable{
	 private Long id;

	    private String activityCode;

	    private String secret;

	    private Long activityId;

	    private Long userId;

	    private String adminQr;

	    private Date creatTime;

	    private Date updateTime;

	    private Integer isDeleted;

	    private Integer isAvailable;

	    private String adminHeadUrl;

	    private String adminName;

	    private String adminPhone;

	    private Integer isSign;

	    private Long orderCodeId;

	    private static final long serialVersionUID = 1L;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getActivityCode() {
			return activityCode;
		}

		public void setActivityCode(String activityCode) {
			this.activityCode = activityCode;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public Long getActivityId() {
			return activityId;
		}

		public void setActivityId(Long activityId) {
			this.activityId = activityId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getAdminQr() {
			return adminQr;
		}

		public void setAdminQr(String adminQr) {
			this.adminQr = adminQr;
		}

		public Date getCreatTime() {
			return creatTime;
		}

		public void setCreatTime(Date creatTime) {
			this.creatTime = creatTime;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public Integer getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Integer isDeleted) {
			this.isDeleted = isDeleted;
		}

		public Integer getIsAvailable() {
			return isAvailable;
		}

		public void setIsAvailable(Integer isAvailable) {
			this.isAvailable = isAvailable;
		}

		public String getAdminHeadUrl() {
			return adminHeadUrl;
		}

		public void setAdminHeadUrl(String adminHeadUrl) {
			this.adminHeadUrl = adminHeadUrl;
		}

		public String getAdminName() {
			return adminName;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}

		public String getAdminPhone() {
			return adminPhone;
		}

		public void setAdminPhone(String adminPhone) {
			this.adminPhone = adminPhone;
		}

		public Integer getIsSign() {
			return isSign;
		}

		public void setIsSign(Integer isSign) {
			this.isSign = isSign;
		}

		public Long getOrderCodeId() {
			return orderCodeId;
		}

		public void setOrderCodeId(Long orderCodeId) {
			this.orderCodeId = orderCodeId;
		}

}
