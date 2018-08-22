package com.people2000.user.model.constant;

/**
 * Created by xiaole on 2015/12/17.
 */
public class ErrorCode {
	/************************ 未知异常(编码:99) ***********************/
	public static final String unknow_exception = "ouser-99";
	/************************ 严重业务错误(防呆)(编码:00) ***********************/
	public static final String serious_bussiness_error = "ouser-00";
	/************************ 系统级错误(编码:11) ***********************/
	public static final String system__error = "ouser-11";
	/************************ 应用级错误(前端参数错误 编码:22) ***********************/
	public static final String application_error = "ouser-22";
	/************************ 业务级错误(service自身处理出错 编码:33) ***********************/
	public static final String bussiness_error = "ouser-33";
	/** sql异常 **/
	public static final String sql_error = "ouser-33-00";
	/************************ 依赖级错误(service内部调用其他接口出错 编码:44) ***********************/
	public static final String dependence_error = "ouser-44";
	/************************ 交互级业务提醒(正常业务逻辑，非错误，需告知用户，如库存不足 编码:55) ***********************/
	/** 入参参数为空 **/
	public static final String input_null = "ouser-55";
	public static final String balance_not_enough = "ouser-55-00";
	public static final String refund_exceed_limit = "ouser-55-01";
	public static final String birthday_update_exceed_limit = "ouser-55-02";
	/** 返回的结果集为空 **/
	public static final String output_null = "ouser-56";
	/************************ 有错误但不影响主流程(主流程已返回正确编码，但也记录不影响主流程的错误 编码:66) ***********************/
	public static final String ordinary_error = "ouser-22";
}
