package com.people2000.common.message;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class AppMessageUtils {

	private static String HOST = "http://sdk.open.api.igexin.com/apiex.htm";
	private static Logger logger = LoggerFactory
			.getLogger(AppMessageUtils.class);

	public static Map<String, Object> pushMessageToSingle(
			AppMessageSettings appMessage) {
		IGtPush push = new IGtPush(HOST, appMessage.getAppKey(),
				appMessage.getMasterSecret());
		ITemplate template = createTemplate(appMessage);

		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 3600 * 1000);
		message.setData(template);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appMessage.getAppId());
		target.setClientId(appMessage.getClientId());
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			logger.error("消息推送异常：" + e.getMessage(), e);
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		} catch (Exception e) {
			logger.error("消息推送异常：" + e.getMessage(), e);
		}
		if (ret != null) {
			return ret.getResponse();
		} else {
			return null;
		}
	}

	public static ITemplate createTemplate(AppMessageSettings appMessage) {
		ITemplate template = null;
		// 默认类型为1
		if (appMessage.getTemplateType() == null) {
			appMessage.setTemplateType(1);
		}

		if (appMessage.getTemplateType() == 1) {
			template = createNotificationTemplate(appMessage);
		} else if (appMessage.getTemplateType() == 2) {
			template = createLinkTemplate(appMessage);
		} else if (appMessage.getTemplateType() == 3) {
			template = createNotyPopLoadTemplate(appMessage);
		} else if (appMessage.getTemplateType() == 4) {
			template = createTransmissionTemplate(appMessage);
		}
		return template;

	}

	/**
	 * 
	 * <p>
	 * 模板1：点击通知打开应用
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年12月17日 下午4:04:57
	 * @param appMessage
	 * @return
	 * @see
	 */
	public static NotificationTemplate createNotificationTemplate(
			AppMessageSettings appMessage) {
		NotificationTemplate template = new NotificationTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appMessage.getAppId());
		template.setAppkey(appMessage.getAppKey());
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		if (appMessage.getTransmissionType() != null) {
			template.setTransmissionType(appMessage.getTransmissionType());
		}
		if (appMessage.getTransmissionContent() != null) {
			template.setTransmissionContent(appMessage.getTransmissionContent());
		}

		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle(appMessage.getTitle());
		style.setText(appMessage.getText());
		// 配置通知栏图标
		style.setLogo(appMessage.getLogo());
		// 配置通知栏网络图标
		if (appMessage.getLogoUrl() != null) {
			style.setLogoUrl(appMessage.getLogoUrl());
		}
		// 设置通知是否响铃，震动，或者可清除
		if (appMessage.getRing() != null) {
			style.setRing(appMessage.getRing());
		}
		if (appMessage.getVibrate() != null) {
			style.setVibrate(appMessage.getVibrate());
		}
		if (appMessage.getClearable() != null) {
			style.setClearable(appMessage.getClearable());
		}
		template.setStyle(style);

		return template;
	}

	/**
	 * 
	 * <p>
	 * 模板2：点击通知，打开网页
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年12月17日 下午4:04:11
	 * @param appMessage
	 * @return
	 * @see
	 */
	public static LinkTemplate createLinkTemplate(AppMessageSettings appMessage) {
		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appMessage.getAppId());
		template.setAppkey(appMessage.getAppKey());

		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle(appMessage.getTitle());
		style.setText(appMessage.getText());
		// 配置通知栏图标
		style.setLogo(appMessage.getLogo());
		// 配置通知栏网络图标
		if (appMessage.getLogoUrl() != null) {
			style.setLogoUrl(appMessage.getLogoUrl());
		}
		// 设置通知是否响铃，震动，或者可清除
		if (appMessage.getRing() != null) {
			style.setRing(appMessage.getRing());
		}
		if (appMessage.getVibrate() != null) {
			style.setVibrate(appMessage.getVibrate());
		}
		if (appMessage.getClearable() != null) {
			style.setClearable(appMessage.getClearable());
		}
		template.setStyle(style);

		// 设置打开的网址地址
		template.setUrl(appMessage.getLinkUrl());
		return template;
	}

	/**
	 * 
	 * <p>
	 * 模板3：点击通知弹窗下载
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年12月17日 下午4:24:34
	 * @param appMessage
	 * @return
	 * @see
	 */
	public static ITemplate createNotyPopLoadTemplate(
			AppMessageSettings appMessage) {
		NotyPopLoadTemplate template = new NotyPopLoadTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appMessage.getAppId());
		template.setAppkey(appMessage.getAppKey());

		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle(appMessage.getTitle());
		style.setText(appMessage.getText());
		// 配置通知栏图标
		style.setLogo(appMessage.getLogo());
		// 配置通知栏网络图标
		if (appMessage.getLogoUrl() != null) {
			style.setLogoUrl(appMessage.getLogoUrl());
		}
		// 设置通知是否响铃，震动，或者可清除
		if (appMessage.getRing() != null) {
			style.setRing(appMessage.getRing());
		}
		if (appMessage.getVibrate() != null) {
			style.setVibrate(appMessage.getVibrate());
		}
		if (appMessage.getClearable() != null) {
			style.setClearable(appMessage.getClearable());
		}
		template.setStyle(style);

		// 设置弹框标题与内容
		template.setPopTitle(appMessage.getPopTitle());
		template.setPopContent(appMessage.getPopContent());
		// 设置弹框显示的图片
		template.setPopImage(appMessage.getPopImage());
		template.setPopButton1(appMessage.getPopButton1());
		template.setPopButton2(appMessage.getPopButton2());
		// 设置下载标题
		template.setLoadTitle(appMessage.getLoadTitle());
		template.setLoadIcon(appMessage.getLoadIcon());
		// 设置下载地址
		template.setLoadUrl(appMessage.getLoadUrl());
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	/**
	 * 
	 * <p>
	 * 模板4：透传消息模板
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年12月17日 下午4:25:54
	 * @param appMessage
	 * @return
	 * @see
	 */
	public static ITemplate createTransmissionTemplate(
			AppMessageSettings appMessage) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appMessage.getAppId());
		template.setAppkey(appMessage.getAppKey());
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(appMessage.getTransmissionType());
		template.setTransmissionContent(appMessage.getTransmissionContent());

		APNPayload payload = new APNPayload();
		// 在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
		payload.setAutoBadge(appMessage.getAutoBadge());
		payload.setContentAvailable(1);
		// payload.setSound("default");
		// payload.setCategory("$由客户端定义");

		// 简单模式APNPayload.SimpleMsg
		payload.setAlertMsg(new APNPayload.SimpleAlertMsg(appMessage.getText()));

		// 字典模式使用APNPayload.DictionaryAlertMsg
		// payload.setAlertMsg(getDictionaryAlertMsg());

		// 添加多媒体资源
		/*
		 * payload.addMultiMedia(new
		 * MultiMedia().setResType(MultiMedia.MediaType.video)
		 * .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
		 * .setOnlyWifi(true));
		 */

		template.setAPNInfo(payload);

		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	public static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(
			AppMessageSettings appMessage) {
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
		alertMsg.setBody("body");
		alertMsg.setActionLocKey("ActionLockey");
		alertMsg.setLocKey("LocKey");
		alertMsg.addLocArg("loc-args");
		alertMsg.setLaunchImage("launch-image");
		// iOS8.2以上版本支持
		alertMsg.setTitle("Title");
		alertMsg.setTitleLocKey("TitleLocKey");
		alertMsg.addTitleLocArg("TitleLocArg");
		return alertMsg;
	}

}
