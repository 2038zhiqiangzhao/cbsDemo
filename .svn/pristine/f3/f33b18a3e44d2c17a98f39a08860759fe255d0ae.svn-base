package com.people2000.user.business.utils.uionlogin;

import java.util.HashMap;
import java.util.Map;

import com.people2000.user.business.utils.SpringUtils;

/**
 * 策略工厂
 * 
 * @author dailf
 * @company ODY
 * @date 2016/7/8
 * @description
 */
public class UnionLoginFactory {

	private static Map<Integer, UnionLoginTemplate> strategyMap = new HashMap<Integer, UnionLoginTemplate>();

	public static void registGateway(Integer gateway, String beanname) {
		strategyMap.put(gateway,
				(UnionLoginTemplate) SpringUtils.getBean(beanname));
	}

	public static UnionLoginTemplate getStrategy(Integer gateway) {
		return strategyMap.get(gateway);
	}
}
