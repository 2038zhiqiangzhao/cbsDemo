package com.people.cbsadmin.model.vo;

import java.io.Serializable;
import java.util.Date;

public class CommunityActivityAdminVo implements Serializable {
    private Long id;

    private String activityCode;

    private String secret;

    private Long activityId;

    private Long userId;

    private String adminQr;

    private Date creatTime;

    private Date updateTime;

    private Byte isDeleted;

    private Byte isAvailable;

    private String adminHeadUrl;

    private String adminName;

    private String adminPhone;

    private Byte isSign;

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
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
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
        this.adminQr = adminQr == null ? null : adminQr.trim();
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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Byte isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getAdminHeadUrl() {
        return adminHeadUrl;
    }

    public void setAdminHeadUrl(String adminHeadUrl) {
        this.adminHeadUrl = adminHeadUrl == null ? null : adminHeadUrl.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public Byte getIsSign() {
        return isSign;
    }

    public void setIsSign(Byte isSign) {
        this.isSign = isSign;
    }

    public Long getOrderCodeId() {
        return orderCodeId;
    }

    public void setOrderCodeId(Long orderCodeId) {
        this.orderCodeId = orderCodeId;
    }
}