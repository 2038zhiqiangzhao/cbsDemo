package com.people2000.user.business.utils.uionlogin;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.people2000.user.business.utils.ConstantUser;
import com.people2000.user.business.utils.HttpUtil;
import com.people2000.user.model.dto.UserUnionLoginDTO;
import com.people2000.user.model.po.ibatis.UUnionConfigPO;

/**
 * 微信联合登录 Created by dailf on 2016/8/8.
 */
@Service("wxLoginService")
public class WXLoginService extends UnionLoginTemplate {
	@Override
	public void registerService() {
		if (null == UnionLoginFactory
				.getStrategy(ConstantUser.union_login_gate.wx)) {
			UnionLoginFactory.registGateway(ConstantUser.union_login_gate.wx,
					"wxLoginService");
		}
	}

	@Override
	public UserUnionLoginDTO getAccessToken(UserUnionLoginDTO userUnionLoginDTO) {

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ userUnionLoginDTO.getAppid() + "&secret="
				+ userUnionLoginDTO.getAppsecret() + "&code="
				+ userUnionLoginDTO.getCode()
				+ "&grant_type=authorization_code";
		String returnStr = (String) HttpUtil.request(url, "get", null);
		// 获取返回的token
		if (null != returnStr
				&& returnStr.contains(UnionLoginFields.access_token)) {
			JSONObject jsonObject = JSONObject.parseObject(returnStr);
			// 获取token
			String accessToken = jsonObject.get(UnionLoginFields.access_token)
					+ "";
			// 获取过期时间
			String expireIn = jsonObject.get(UnionLoginFields.expires_in) + "";
			// 获取openid
			String openid = jsonObject.get(UnionLoginFields.openid) + "";
			// FIXME 获取refresh_token
			String refresh_token = jsonObject
					.get(UnionLoginFields.refresh_token) + "";
			userUnionLoginDTO.setToken(accessToken);
			userUnionLoginDTO.setOpenid(openid);
			userUnionLoginDTO.setExpire_in(Long.parseLong(expireIn));
			userUnionLoginDTO.setRefreshToken(refresh_token);
		} else {
			throw new RuntimeException("获取WX_token失败：" + returnStr);
		}
		return userUnionLoginDTO;
	}

	@Override
	public UserUnionLoginDTO getOpenId(UserUnionLoginDTO userUnionLoginDTO) {
		return userUnionLoginDTO;
	}

	@Override
	public String getRelatedParams(UserUnionLoginDTO userUnionLoginDTO) {
		UUnionConfigPO uUnionConfigPO = super
				.getUnionConfigInfo(userUnionLoginDTO);
		String scope = "";
		String baseUrl = "";
		// h5
		if (uUnionConfigPO.getPlatform() == 3) {
			scope = "snsapi_userinfo";
			baseUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?";
		} else {
			scope = "snsapi_login";
			baseUrl = "https://open.weixin.qq.com/connect/qrconnect?";
		}
		String returnUrl = baseUrl + "appid=" + uUnionConfigPO.getAppId()
				+ "&redirect_uri=" + uUnionConfigPO.getRedirectUri()
				+ "&response_type=code&scope=" + scope + "&state="
				+ userUnionLoginDTO.getState() + "#wechat_redirect";
		return returnUrl;
	}

	@Override
	public UserUnionLoginDTO getUserInfo(UserUnionLoginDTO userUnionLoginDTO)
			throws Exception {
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token="
				+ userUnionLoginDTO.getToken() + "&openid="
				+ userUnionLoginDTO.getOpenid();
		String returnStr = (String) HttpUtil.request(url, "get", null);
		if (!returnStr.contains(UnionLoginFields.wx_errcode)) {
			JSONObject jsonObject = JSONObject.parseObject(returnStr);
			// 取系统毫秒后6位
			String millis = System.currentTimeMillis() + "";
			// 暂时只获取用户名
			userUnionLoginDTO.setUserName(jsonObject
					.get(UnionLoginFields.wx_nickname)
					+ millis.substring(millis.length() - 6, millis.length()));
			userUnionLoginDTO.setNewUserName(jsonObject
					.get(UnionLoginFields.wx_nickname) + "");
			userUnionLoginDTO.setUnionId(jsonObject
					.get(UnionLoginFields.wx_unionid) + "");
			userUnionLoginDTO.setOpenid(userUnionLoginDTO.getUnionId());
		}
		return userUnionLoginDTO;
	}

}
