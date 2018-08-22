package com.people2000.user.business.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.people2000.common.base.numeric.RandomCodeFactory;
import com.people2000.common.cache.CacheProxy;
import com.people2000.common.log.LogUtils;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserUnionLoginDTO;
import com.people2000.user.model.po.ibatis.UserCookie;

/**
 * @author crc
 * @time 2015-5-29 下午5:04:09
 * @description <pre>
 * <p/>
 * </pre>
 */
public class TicketUtils {

	private static final Logger log = LoggerFactory
			.getLogger(TicketUtils.class);

	private static final int ut_timeout = 30 * 24 * 60 * 60; // 秒

	private static final int CookieLife = 30;// 30天

	public static UserCookie generateOpenIdTicket(UserCache userCache,
			CacheProxy proxy, HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 设置访问的域名
			SsoClientUtil.setDomain(req.getServerName());

			String ut = UUID.randomUUID().toString().replace("-", "");
			if (null != req && null != resp) {
				// String domain = getDomain(req);
				Cookie cookie = new Cookie("ut", ut);
				cookie.setDomain(SsoClientUtil.getDomain()); // 请用自己的域
				cookie.setPath("/");
				resp.addCookie(cookie);
			}
			Ticket t = new Ticket();
			t.setTime(new Date().getTime());
			t.setUser(userCache);
			proxy.put(ut, t, ut_timeout);
			UserCookie userCookie = new UserCookie();
			userCookie.setUserId(userCache.getId());
			userCookie.setType(1);
			userCookie.setCookieName("ut");
			userCookie.setCookieValue(ut);
			userCookie.setDomain(SsoClientUtil.getDomain());
			return userCookie;
		} catch (Exception e) {
			LogUtils.getLogger(TicketUtils.class).error("生成UserCookie异常", e);
			return null;
		}
	}

	/**
	 * TODO lws: 生成ut、写cookie、保存缓存 , Ticket是否可废弃？写缓存统一要companyId？
	 * 
	 * @param userCache
	 * @param proxy
	 * @param req
	 * @param resp
	 * @return
	 */
	public static UserCookie generateTicket(UserCache userCache,
			CacheProxy proxy, HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 设置访问的域名
			SsoClientUtil.setDomain(req.getServerName());

			String ut = UUID.randomUUID().toString().replace("-", "");

			if (null != req && null != resp) {
				Cookie cookie = new Cookie("ut", ut);
				cookie.setDomain(SsoClientUtil.getDomain()); // 请用自己的域
				if (userCache.isAutoLogin()) {// 自动登录cookie保存一天,否则关闭浏览器,cookie失效
					cookie.setMaxAge((int) (CookieLife * 24 * 60 * 60)); // 关闭浏览器后，cookie立即失效
				}
				cookie.setPath("/");
				resp.addCookie(cookie);
			}

			Ticket t = new Ticket();
			t.setTime(new Date().getTime());
			t.setUser(userCache);
			proxy.put(ut, t, ut_timeout);

			UserCookie userCookie = new UserCookie();
			userCookie.setUserId(userCache.getId());
			userCookie.setType(1);
			userCookie.setCookieName("ut");
			userCookie.setCookieValue(ut);
			userCookie.setDomain(SsoClientUtil.getDomain());
			return userCookie;
		} catch (Exception e) {
			LogUtils.getLogger(TicketUtils.class).error("生成UserCookie异常", e);
			return null;
		}
	}

	/**
	 * ut生成（联合登录专用）
	 * 
	 * @param userCache
	 * @param proxy
	 * @param companyId
	 * @param ut
	 * @return
	 */
	public static UserCookie generateTicket(UserCache userCache,
			CacheProxy proxy, Long companyId, String ut) {
		try {
			/*
			 * cookie.setPath("/"); resp.addCookie(cookie);
			 */
			Ticket t = new Ticket();
			t.setTime(new Date().getTime());
			t.setUser(userCache);
			proxy.put(ut, t, ut_timeout);

			UserCookie userCookie = new UserCookie();
			userCookie.setUserId(userCache.getId());
			userCookie.setType(1);
			userCookie.setCookieName("ut");
			userCookie.setCookieValue(ut);
			userCookie.setDomain(SsoClientUtil.getDomain());
			return userCookie;
		} catch (Exception e) {
			LogUtils.getLogger(TicketUtils.class).error("生成UserCookie异常", e);
			return null;
		}
	}

	/*
	 * public static String getDomain(HttpServletRequest req) { // String domain
	 * = SsoClientUtil.getDomain(); //从表中查询 String domain = null;
	 * DomainInfoReadClient instanst = DomainInfoReadClient.getInstanst();
	 * InputDTO<String> inputDto = new InputDTO<String>(); String accessDomain =
	 * req.getServerName();//获得网站域名 inputDto.setData(accessDomain);
	 * OutputDTO<DomainInfoDTO> outputDto =
	 * instanst.queryByAccessDomain(inputDto); if
	 * ("0".equals(outputDto.getCode())) { DomainInfoDTO data =
	 * outputDto.getData(); domain = data.getCookieDomain(); }
	 * req.getContextPath(); String serverName = req.getServerName(); if
	 * (!serverName.contains(SsoClientUtil.getDomain())) {
	 * LogUtils.getLogger(TicketUtils.class).debug("配置文件中的domain=" + domain +
	 * "  用户访问的domain=" + serverName); } return domain; }
	 * 
	 * public static Integer getDomainType(HttpServletRequest req) { // String
	 * domain = SsoClientUtil.getDomain(); //从表中查询 Integer domainType = null;
	 * DomainInfoReadClient instanst = DomainInfoReadClient.getInstanst();
	 * InputDTO<String> inputDto = new InputDTO<String>(); String accessDomain =
	 * req.getServerName(); inputDto.setData(accessDomain);
	 * OutputDTO<DomainInfoDTO> outputDto =
	 * instanst.queryByAccessDomain(inputDto); if
	 * ("0".equals(outputDto.getCode())) { DomainInfoDTO data =
	 * outputDto.getData(); domainType = data.getIsPlatform(); }else{
	 * domainType=-1; } return domainType; }
	 */
	public static boolean generateGuidAndSid(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			StringBuilder cookieBuffer = new StringBuilder();
			cookieBuffer.append(SsoClientUtil.getCookieName()).append("=")
					.append(RandomCodeFactory.generateGUID())
					.append(";Path=/;Domain=")
					.append(SsoClientUtil.getDomain()).append(";HTTPOnly");
			resp.setHeader("Set-Cookie", cookieBuffer.toString());
			return true;
		} catch (Exception e) {
			LogUtils.getLogger(TicketUtils.class).error("生成cookie异常", e);
			return false;
		}
	}

	public static boolean deleteTicket(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			String ut = getUT(req);
			if (StringUtils.isBlank(ut)) {
				return true;
			}
			StringBuilder cookieBuffer = new StringBuilder();
			cookieBuffer.append(SsoClientUtil.getCookieName()).append("=")
					.append(";Path=/;Domain=")
					.append(SsoClientUtil.getDomain())
					.append(";Max-Age=0;HTTPOnly");
			resp.setHeader("Set-Cookie", cookieBuffer.toString());
			return true;
		} catch (Exception e) {
			LogUtils.getLogger(TicketUtils.class)
					.error("response设置cookie异常", e);
			return false;
		}
	}

	public static String getUT(HttpServletRequest req) {
		String ut = null;
		ut = req.getParameter("ut");
		if (StringUtils.isNotBlank(ut)) {
			ut = ut.trim();
			ut = ut.toLowerCase();
			ut = ut.replaceAll(" ", "");
			ut = ut.replaceAll("\t", "");
			ut = ut.replaceAll("\r", "");
			ut = ut.replaceAll("\n", "");
			return ut;
		}
		Cookie[] cookies = req.getCookies();
		if (ArrayUtils.isEmpty(cookies)) {
			return "";
		}
		for (int i = 0; i < cookies.length; i++) {
			if (SsoClientUtil.getCookieName().equalsIgnoreCase(
					cookies[i].getName())) {
				ut = cookies[i].getValue();
				break;
			}
		}
		if (StringUtils.isBlank(ut)) {
			return "";
		}
		return ut;
	}

	/**
	 * TODO lws: 看起来作用是根据ut去查缓存的usercache，为usercache续命
	 * 1、DefaultCompany写死的，companyId写死的，为什么读取设置缓存还要判断，
	 * 判断操作是否在cacheProxy里做了？或者0当companyId占位符，这样处理逻辑一致 2、续命为什么要小于10分组的才续
	 * 
	 * @param proxy
	 * @param ut
	 * @return
	 */
	public static UserCache getUser(CacheProxy proxy, String ut) {

		/*
		 * try { if (StringUtils.isBlank(ut)) { return null; } //Ticket t =
		 * (Ticket) proxy.get(ut); Ticket t=new Ticket(); //if (t == null) {
		 * Long companyId=DomainCache.getCompanyId(); List<UEncryptInfo>
		 * listEncrypt=(List<UEncryptInfo>) proxy.get("encryptInfo"); String
		 * key=null;
		 * 
		 * if(null!=listEncrypt&&null!=companyId){ for(UEncryptInfo
		 * info:listEncrypt){ if(companyId.equals(info.getCompanyId())){
		 * key=info.getEncryptKey(); break; } } }
		 * 
		 * if(null==key){ key="odianyun";//没有拿到key,默认odianyun }
		 * 
		 * String dencryptUt=UtEncryptUtils.dencryptUt(ut, companyId);
		 * if(StringUtils.isNotEmpty(dencryptUt)&&dencryptUt.length()>32){
		 * if(null
		 * !=companyId&&!(dencryptUt.substring(32)).equals(companyId+"")){
		 * //companyId不符合 return null; } t = (Ticket)
		 * proxy.get(dencryptUt.substring(0, 32)); }
		 * 
		 * t = (Ticket) proxy.get(dencryptUt); if(t==null){ //deleteTicket(req,
		 * resp); return null; } //} long last = t.getTime(); long now = new
		 * Date().getTime(); // 时间计算错误, 乘法优于除法. long remain = ut_timeout * 1000
		 * * 60 - (now - last); // 缓存剩余分钟数 if (remain <= 0) { return null; } if
		 * (remain < 600000) { t.setTime(now); proxy.put(ut, t, ut_timeout); }
		 * return t.getUser(); } catch (Exception e) {
		 * LogUtils.getLogger(TicketUtils.class).error("获取用户异常", e); return
		 * null; }
		 */

		try {
			if (StringUtils.isBlank(ut)) {
				return null;
			}
			Ticket t = null;
			t = (Ticket) proxy.get(ut);
			if (t == null) {
				return null;
			}
			long last = t.getTime();
			long now = new Date().getTime();
			// 时间计算错误, 乘法优于除法.
			long remain = ut_timeout * 1000L - (now - last); // 缓存剩余分钟数
			if (remain <= 0) {
				return null;
			}
			if (remain < 600000) {
				t.setTime(now);
				proxy.put(ut, t, ut_timeout);
			}
			return t.getUser();
		} catch (Exception e) {
			LogUtils.getLogger(TicketUtils.class).error("获取用户异常", e);
			return null;
		}

	}

	@SuppressWarnings("unused")
	public static UserCache getUser(HttpServletRequest req,
			HttpServletResponse resp, CacheProxy proxy) {

		try {
			String ut = getUT(req);
			return getUser(proxy, ut);
		} catch (Exception e) {
			LogUtils.getLogger(TicketUtils.class).error("获取UserCache异常", e);
			return null;
		}
	}

	/**
	 * TODO Ticket考虑彻底废弃，DefaultCompany的实现也有待商榷，LogUtils存在有啥意义？
	 * 
	 * @param userCache
	 * @param proxy
	 * @param ut
	 * @return
	 */
	public static boolean generateTicket(UserCache userCache, CacheProxy proxy,
			String ut) {

		Ticket t = new Ticket();
		t.setTime(new Date().getTime());
		t.setUser(userCache);

		proxy.put(ut, t, ut_timeout);
		return true;
	}

	public static String getUt(HttpServletRequest req) {
		String ut = getUT(req);
		return ut;
	}

	public static boolean deleteCookie(String ut, CacheProxy proxy) {
		proxy.remove(ut);
		return true;
	}

	/**
	 * 缓存中加入联合登录openId
	 * 
	 * @param ut
	 * @param proxy
	 * @return
	 */
	public static boolean setUnionOpenId(UserUnionLoginDTO userUnionLoginDTO,
			String ut, CacheProxy proxy) {
		if (!StringUtil.isBlank(ut)
				&& !StringUtil.isBlank(userUnionLoginDTO.getOpenid())) {
			proxy.put(ut + "openid", userUnionLoginDTO.getOpenid(), ut_timeout);
			proxy.put(ut + "username", userUnionLoginDTO.getNewUserName(),
					ut_timeout);

			return Boolean.TRUE;
		} else {
			throw new RuntimeException("ut或openId为空");
		}
	}

	/**
	 * 获取openId
	 * 
	 * @param ut
	 * @param proxy
	 * @return
	 */
	public static Map<String, String> getUnionOpenId(String ut, CacheProxy proxy) {
		Map<String, String> thisMap = new HashMap<>();
		if (!StringUtil.isBlank(ut)) {
			String openid = proxy.get(ut + "openid") + "";
			String username = proxy.get(ut + "username") + "";
			if (StringUtil.isBlank(openid) || "null".equals(openid)) {
				throw new RuntimeException("无法获取到openId或username:ut=" + ut);
			} else {
				thisMap.put("openid", openid);
				thisMap.put("username", username);
			}
			return thisMap;
		} else {
			throw new RuntimeException("ut不能为空");
		}
	}
}
