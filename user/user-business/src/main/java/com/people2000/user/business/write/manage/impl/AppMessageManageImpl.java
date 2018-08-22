package com.people2000.user.business.write.manage.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.exception.BusinessException;
import com.people2000.common.message.AppMessageSettings;
import com.people2000.common.message.AppMessageUtils;
import com.people2000.user.business.write.manage.AppMessageManage;
import com.people2000.user.business.write.manage.PushMessageWriteManage;
import com.people2000.user.model.dto.AppMessageDTO;
import com.people2000.user.model.dto.PushMessageInputDTO;
import com.people2000.user.model.dto.PushMessageOutputDTO;

/**
 * App推送消息业务类
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017年11月2日 下午5:26:05,content:
 * </p>
 * 
 * @author dusai
 * @date 2017年11月2日 下午5:26:05
 * @since
 * @version
 */
@Component("appMessage")
public class AppMessageManageImpl implements AppMessageManage {

	@Value("${apppush.host}")
	private String host;// 接口地址
	@Value("${apppush.appId}")
	private String appId;// app应用id
	@Value("${apppush.appKey}")
	private String appKey;// app应用key
	@Value("${apppush.masterSecret}")
	private String masterSecret;// app应用秘钥
	@Value("${apppush.logoUrl}")
	private String logoUrl;// app应用图标
	@Value("${apppush.logo}")
	private String logo;// app应用图标

	private Logger logger = LoggerFactory.getLogger(AppMessageManageImpl.class);

	@Resource
	private PushMessageWriteManage pushMessageWriteManage;

	/**
	 * 
	 * <p>
	 * 单个客户端消息推送
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月21日 下午2:57:48
	 * @param message
	 * @return
	 * @see com.people2000.cbsadmin.business.write.AppMessageManage#pushMessageToSingle(com.people2000.cbsadmin.model.dto.AppMessageDTO)
	 */
	@Override
	public int pushMessageToSingleClient(AppMessageDTO message)
			throws BusinessException {
		AppMessageSettings settings = new AppMessageSettings();
		BeanUtils.copyProperties(message, settings);
		settings.setAppId(appId);
		settings.setAppKey(appKey);
		settings.setMasterSecret(masterSecret);
		settings.setLogoUrl(StringUtils.isEmpty(logoUrl) ? "" : logoUrl);
		settings.setLogo(StringUtils.isEmpty(logo) ? "" : logo);
		settings.setAutoBadge(message.getAutoBadge());
		if (message.getAppType() != null
				&& message.getAppType().intValue() == 2) {
			settings.setTemplateType(4);// IOS强制使用透传消息模板（其他对ios不适用）
		}
		settings.setTransmissionType(message.getTransmissionType());// 点击消息立即启动应用
																	// (如果是透传消息
																	// IOS消息栏有消息，点击打开app；Android消息栏无消息，直接打开app)
		settings.setTransmissionContent(message.getTransmissionContent());
		Map<String, Object> resultMap = AppMessageUtils
				.pushMessageToSingle(settings);
		Object result = resultMap.get("result");
		if (result != null && result.toString().equals("ok")) {
			return 0;
		}
		logger.error("消息推送失败" + result);
		return -1;
	}

	/**
	 * 
	 * <p>
	 * 给一个用户的所有客户端推送消息
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月21日 下午3:08:46
	 * @param userDTO
	 * @param message
	 * @return
	 * @throws BusinessException
	 * @see com.people2000.cbsadmin.business.write.AppMessageManage#pushMessageToUser(com.people2000.cbsadmin.model.dto.UserDTO,
	 *      com.people2000.cbsadmin.model.dto.AppMessageDTO)
	 */
	@Override
	public int pushMessageToUser(AppMessageDTO message)
			throws BusinessException {
		if (message.getUserId() == null) {
			throw new BusinessException("Params Error", "user id is null");
		}
		int result = 0;
		PushMessageInputDTO querys = new PushMessageInputDTO();
		querys.setUserId(message.getUserId());
		List<PushMessageOutputDTO> pmList = pushMessageWriteManage
				.findByConditions(querys);
		for (PushMessageOutputDTO pm : pmList) {
			message.setClientId(pm.getDeviceId());
			message.setAppType(pm.getAppType());
			// message.setAutoBadge(message.getAutoBadge());// app角标数字+1
			// message.setTransmissionType(message.getTransmissionType());//
			// 1:立即打开app 2等待用户主动打开app
			int rst = pushMessageToSingleClient(message);
			if (rst == -1) {
				result = rst;
			}
		}
		return result;
	}

}
