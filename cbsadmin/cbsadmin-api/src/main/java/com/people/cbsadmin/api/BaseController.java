package com.people.cbsadmin.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 基类action
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:zhuhui,date:2017年3月29日 上午11:12:13,content </p>
 * @author zhuhui
 * @date 2017年3月29日 上午11:12:13
 * @since
 * @version
 */
public class BaseController {
	
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

	protected Map<String, Object> noLogin(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "99");
		map.put("data", object);
		return map;
	}
}
