package com.people2000.common.base.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class ResultObjct implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 4143052904455135173L;

	public static final String SUCCESS = "0";
	public static final String FAIL = "-1";

	private String code;// 返回状态码
	private String message;// 消息内容
	private Object data;// 数据对象
	private Integer total;// 总数

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public static ResultObjct newInstance(String resultObjctJson) {
		ResultObjct resultObjct = JSONObject.parseObject(resultObjctJson,
				ResultObjct.class);
		return resultObjct;
	}

	public static ResultObjct returnSuccess(Object data) {
		ResultObjct resultObjct = new ResultObjct();
		resultObjct.setCode(SUCCESS);
		resultObjct.setData(data);
		return resultObjct;
	}

	public static ResultObjct returnSuccess(Object data, String message) {
		ResultObjct resultObjct = new ResultObjct();
		resultObjct.setCode(SUCCESS);
		resultObjct.setMessage(message);
		resultObjct.setData(data);
		return resultObjct;
	}

	public static ResultObjct returnFail(String errorMessage) {
		ResultObjct resultObjct = new ResultObjct();
		resultObjct.setCode(FAIL);
		resultObjct.setMessage(errorMessage);
		return resultObjct;
	}

	public static ResultObjct returnFail(Object errorMessage) {
		ResultObjct resultObjct = new ResultObjct();
		resultObjct.setCode(FAIL);
		resultObjct.setMessage(errorMessage == null ? "" : errorMessage
				.toString());
		return resultObjct;
	}

}
