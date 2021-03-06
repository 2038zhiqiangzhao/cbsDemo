package com.people.cbsadmin.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table order_pay_notify
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class OrderPayNotify implements Serializable {
    /**
     * Database Column Remarks:
     *   ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   订单号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.order_code
     *
     * @mbg.generated
     */
    private String orderCode;

    /**
     * Database Column Remarks:
     *   来源 0微信 1支付宝
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.source
     *
     * @mbg.generated
     */
    private Integer source;

    /**
     * Database Column Remarks:
     *   处理状态 0待处理 1已处理
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * Database Column Remarks:
     *   通知返回状态码 SUCCESS:成功
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.result_code
     *
     * @mbg.generated
     */
    private String resultCode;

    /**
     * Database Column Remarks:
     *   通知返回数据json
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.result_data
     *
     * @mbg.generated
     */
    private String resultData;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.create_user
     *
     * @mbg.generated
     */
    private Long createUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_pay_notify.update_user
     *
     * @mbg.generated
     */
    private Long updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_pay_notify
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.id
     *
     * @return the value of order_pay_notify.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.id
     *
     * @param id the value for order_pay_notify.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.order_code
     *
     * @return the value of order_pay_notify.order_code
     *
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.order_code
     *
     * @param orderCode the value for order_pay_notify.order_code
     *
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.source
     *
     * @return the value of order_pay_notify.source
     *
     * @mbg.generated
     */
    public Integer getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.source
     *
     * @param source the value for order_pay_notify.source
     *
     * @mbg.generated
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.status
     *
     * @return the value of order_pay_notify.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.status
     *
     * @param status the value for order_pay_notify.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.result_code
     *
     * @return the value of order_pay_notify.result_code
     *
     * @mbg.generated
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.result_code
     *
     * @param resultCode the value for order_pay_notify.result_code
     *
     * @mbg.generated
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode == null ? null : resultCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.result_data
     *
     * @return the value of order_pay_notify.result_data
     *
     * @mbg.generated
     */
    public String getResultData() {
        return resultData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.result_data
     *
     * @param resultData the value for order_pay_notify.result_data
     *
     * @mbg.generated
     */
    public void setResultData(String resultData) {
        this.resultData = resultData == null ? null : resultData.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.create_time
     *
     * @return the value of order_pay_notify.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.create_time
     *
     * @param createTime the value for order_pay_notify.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.create_user
     *
     * @return the value of order_pay_notify.create_user
     *
     * @mbg.generated
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.create_user
     *
     * @param createUser the value for order_pay_notify.create_user
     *
     * @mbg.generated
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.update_time
     *
     * @return the value of order_pay_notify.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.update_time
     *
     * @param updateTime the value for order_pay_notify.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_pay_notify.update_user
     *
     * @return the value of order_pay_notify.update_user
     *
     * @mbg.generated
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_pay_notify.update_user
     *
     * @param updateUser the value for order_pay_notify.update_user
     *
     * @mbg.generated
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}