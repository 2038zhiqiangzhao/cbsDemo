package com.people2000.user.business.read.manage;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface OppDataReadManage {

	/**
	 * 根据指定值获取配置数据
	 * 
	 * @param key
	 * @return
	 */
	public JSONObject getValueByConfigKey(String key, int productId,
			Long companyId);

	/**
	 * 根据一组值获取配置数据
	 * 
	 * @param key
	 * @return
	 */
	public JSONObject getValueByConfigKey(List<String> key, int productId,
			Long companyId);

	/**
	 * 根据指定值获取其子配置
	 * 
	 * @param key
	 * @return
	 */
	public JSONObject getChlidValueByConfigKey(String key, int productId,
			Long companyId);
}
