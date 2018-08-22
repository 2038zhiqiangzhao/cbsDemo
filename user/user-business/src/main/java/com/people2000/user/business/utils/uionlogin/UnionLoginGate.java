package com.people2000.user.business.utils.uionlogin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.people2000.user.model.dto.UserUnionLoginDTO;

/**
 * 联合登录统一入口 Created by dailf on 2016/8/5.
 */
public class UnionLoginGate {

	private static final Log logger = LogFactory.getLog(UnionLoginGate.class);

	/**
	 * 保存联合登录用户信息
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 */
	public static Object savaUnionUserInfoWithTx(
			UserUnionLoginDTO userUnionLoginDTO) throws Exception {
		Integer gateway;
		if (null != userUnionLoginDTO.getGateway()) {
			gateway = userUnionLoginDTO.getGateway();
		} else {
			String state = userUnionLoginDTO.getState();
			// 从state中获取网关session_gateway
			gateway = Integer.valueOf(state.split("_")[1]);
		}
		logger.info("gateway:" + gateway);
		// 获取网关
		UnionLoginTemplate unionLoginTemplate = UnionLoginFactory
				.getStrategy(gateway);

		userUnionLoginDTO.setGateway(gateway);
		return unionLoginTemplate.savaUnionUserInfoWithTx(userUnionLoginDTO);
	}

	/**
	 * 获取支付前的相关参数
	 * 
	 * @param userUnionLoginDTO
	 * @return
	 */
	public static String getRelatedParams(UserUnionLoginDTO userUnionLoginDTO) {
		// 获取网关
		Integer gateway = userUnionLoginDTO.getGateway();
		if (null == gateway) {
			throw new RuntimeException("gateway不能为空");
		}
		UnionLoginTemplate unionLoginTemplate = UnionLoginFactory
				.getStrategy(gateway);

		return unionLoginTemplate.getRelatedParams(userUnionLoginDTO);
	}
}
