package com.people.cbsadmin.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 基类action
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:dusai,date:2017年3月29日 上午11:12:13,content </p>
 * @author dusai
 * @date 2017年3月29日 上午11:12:13
 * @since
 * @version
 */
public class BaseAction {
	
	protected Map<String, Object> success(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("data", object);
		return map;
	}
	
	protected Map<String, Object> success(Object object,int num) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		map.put("data", object);
		map.put("total", num);
		return map;
	}
	
	protected Map<String, Object> fail(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "-1");
		map.put("data", object);
		return map;
	}
	
	protected Map<String, Object> fail(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "-1");
		map.put("message", message);
		return map;
	}

	protected Map<String, Object> noLogin(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "99");
		map.put("data", object);
		return map;
	}
}
