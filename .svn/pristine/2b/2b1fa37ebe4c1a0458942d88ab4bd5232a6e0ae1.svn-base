//package com.people2000.user.business.utils.uionlogin;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.springframework.stereotype.Service;
//
//import com.qq.connect.javabeans.qzone.UserInfoBean;
//import com.qq.connect.utils.json.JSONObject;
//import com.people2000.user.business.utils.ConstantUser;
//import com.people2000.user.business.utils.HttpUtil;
//import com.people2000.user.model.dto.UserUnionLoginDTO;
//import com.people2000.user.model.po.ibatis.UUnionConfigPO;
//
///**
// * qq支持的方法 Created by dailf on 2016/8/3.
// */
//@Service("qqLoginService")
//public class QQLoginService extends UnionLoginTemplate {
//
//	@Override
//	public void registerService() {
//		if (null == UnionLoginFactory
//				.getStrategy(ConstantUser.union_login_gate.qq)) {
//			UnionLoginFactory.registGateway(ConstantUser.union_login_gate.qq,
//					"qqLoginService");
//		}
//	}
//
//	@Override
//	public UserUnionLoginDTO getAccessToken(UserUnionLoginDTO userUnionLoginDTO) {
//
//		String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="
//				+ userUnionLoginDTO.getAppid()
//				+ "&client_secret="
//				+ userUnionLoginDTO.getAppsecret()
//				+ "&code="
//				+ userUnionLoginDTO.getCode()
//				+ "&redirect_uri="
//				+ userUnionLoginDTO.getRedirect_uri();
//		String returnStr = (String) HttpUtil.request(url, "get", null);
//		// 获取返回的token
//		if (null != returnStr
//				&& returnStr.contains(UnionLoginFields.access_token)) {
//			// 获取token
//			String accessToken = UnionLoginUtils.getUrlValue(returnStr,
//					UnionLoginFields.access_token);
//			userUnionLoginDTO.setToken(accessToken);
//			// 获取过期时间
//			String expireIn = UnionLoginUtils.getUrlValue(returnStr,
//					UnionLoginFields.expires_in);
//			userUnionLoginDTO.setExpire_in(Long.parseLong(expireIn));
//			// 获取refreshToken
//			String refreshToken = UnionLoginUtils.getUrlValue(returnStr,
//					UnionLoginFields.refresh_token);
//			userUnionLoginDTO.setRefreshToken(refreshToken);
//		} else {
//			throw new RuntimeException("获取QQ_token失败：" + returnStr);
//		}
//		return userUnionLoginDTO;
//	}
//
//	@Override
//	public UserUnionLoginDTO getOpenId(UserUnionLoginDTO userUnionLoginDTO) {
//		String url = "https://graph.qq.com/oauth2.0/me?access_token="
//				+ userUnionLoginDTO.getToken();
//		String returnStr = (String) HttpUtil.request(url, "get", null);
//		Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(
//				returnStr);
//		if (m.find()) {
//			String openid = m.group(1);
//			userUnionLoginDTO.setOpenid(openid);
//		} else {
//			throw new RuntimeException("获取QQ_token失败：" + m);
//		}
//		return userUnionLoginDTO;
//	}
//
//	@Override
//	public String getRelatedParams(UserUnionLoginDTO userUnionLoginDTO) {
//		UUnionConfigPO uUnionConfigPO = super
//				.getUnionConfigInfo(userUnionLoginDTO);
//		String returnUrl = "https://graph.qq.com/oauth2.0/authorize?client_id="
//				+ uUnionConfigPO.getAppId() + "&redirect_uri="
//				+ uUnionConfigPO.getRedirectUri()
//				+ "&response_type=code&scope=do_like&state="
//				+ userUnionLoginDTO.getState();
//		if (uUnionConfigPO.getPlatform() == 3) {
//			returnUrl += "&display=mobile";
//		}
//		return returnUrl;
//	}
//
//	@Override
//	public UserUnionLoginDTO getUserInfo(UserUnionLoginDTO userUnionLoginDTO)
//			throws Exception {
//		// UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
//		String url = "https://graph.qq.com/user/get_user_info?access_token="
//				+ userUnionLoginDTO.getToken() + "&oauth_consumer_key="
//				+ userUnionLoginDTO.getAppid() + "&openid="
//				+ userUnionLoginDTO.getOpenid() + "&format=json";
//		String returnStr = (String) HttpUtil.request(url, "get", null);
//
//		String formatStr = returnStr.replaceAll("(\r\n|\r|\n|\n\r)", "");
//
//		JSONObject jsonObject = new JSONObject(formatStr);
//		UserInfoBean userInfoBean = new UserInfoBean(jsonObject);
//		if (userInfoBean.getRet() == 0) {
//			// 取系统毫秒后6位
//			String millis = System.currentTimeMillis() + "";
//			// 暂时只获取用户名
//			userUnionLoginDTO.setUserName(userInfoBean.getNickname());
//			userUnionLoginDTO.setNewUserName(userInfoBean.getNickname() + "");
//		}
//
//		userUnionLoginDTO.setUnionId(userUnionLoginDTO.getOpenid());
//
//		return userUnionLoginDTO;
//	}
//
//}
