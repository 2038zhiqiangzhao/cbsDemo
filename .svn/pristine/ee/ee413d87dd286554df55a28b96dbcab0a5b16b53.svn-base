package com.people2000.user.business.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.people2000.common.cache.CacheProxy;
import com.people2000.user.model.dto.UserCache;
import com.people2000.user.model.dto.UserUnionLoginDTO;
import com.people2000.user.model.po.ibatis.UserCookie;

/**
 * @author crc
 * @time 2015-5-29 下午5:06:04
 * @description <pre>
 * <p/>
 * </pre>
 */
public class SsoClientUtil {

	private static final Log logger = LogFactory.getLog(SsoClientUtil.class);

	private static CacheProxy proxy;

	private static UserCache testUser;

	private static String domain;

	private static String cookieName;

	private static boolean init = false;

	// CLIENT配置文件路径（相对）
	private final static String CLIENT_FILE_PATH = "user/user-web/client.properties";

	private static void init() {

		File clientFile = new File(System.getProperty("global.config.path"),
				CLIENT_FILE_PATH);

		try (InputStream is = new BufferedInputStream(new FileInputStream(
				clientFile))) {
			Properties properties = new Properties();
			properties.load(is);
			domain = properties.get("domain").toString();
			cookieName = properties.get("cookieName").toString();

			init = true;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		/*
		 * init=true; domain = "localhost"; cookieName = "ut";
		 */

		proxy = CacheCommUtils.getCache();//
	}

	public static String getDomain() {
		return domain;
	}

	public static String getCookieName() {
		return cookieName;
	}

	public static UserCache getUser(HttpServletRequest req,
			HttpServletResponse resp) {
		if (init == false) {
			init();
		}
		return TicketUtils.getUser(req, resp, proxy);
	}

	public static String getUt(HttpServletRequest req) {
		if (init == false) {
			init();
		}
		return TicketUtils.getUt(req);
	}

	public static UserCache getUser(String ut) {
		if (init == false) {
			init();
		}
		return TicketUtils.getUser(proxy, ut);
	}

	public static UserCookie login(UserCache userCache, HttpServletRequest req,
			HttpServletResponse resp) {
		if (init == false) {
			init();
		}
		return TicketUtils.generateTicket(userCache, proxy, req, resp);
	}

	public static UserCookie unionLogin(UserCache userCache, Long companyId,
			String ut) {
		if (init == false) {
			init();
		}
		return TicketUtils.generateTicket(userCache, proxy, companyId, ut);
	}

	public static boolean login(UserCache userCache, String ut) {
		if (init == false) {
			init();
		}
		return TicketUtils.generateTicket(userCache, proxy, ut);
	}

	public static boolean logout(HttpServletRequest req,
			HttpServletResponse resp) {
		if (init == false) {
			init();
		}
		return TicketUtils.deleteTicket(req, resp);
	}

	public static boolean logout(String ut) {
		if (init == false) {
			init();
		}
		return TicketUtils.deleteCookie(ut, proxy);
	}

	@SuppressWarnings("unchecked")
	public static String getCurrentUrl(HttpServletRequest req,
			HttpServletResponse resp) {
		if (init == false) {
			init();
		}
		Iterator<Map.Entry<String, Object>> it = req.getParameterMap()
				.entrySet().iterator();
		StringBuffer params = new StringBuffer();
		while (it.hasNext()) {
			if (StringUtils.isNotBlank(params.toString())) {
				params.append("&");
			}
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it
					.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (val instanceof String[]) {
				String[] values = (String[]) val;
				for (int i = 0; i < values.length; i++) {
					if (i > 0) {
						params.append("&");
					}
					params.append(key).append("=").append(values[i]);
				}
			} else {
				if (val != null) {
					params.append(key).append("=").append(val.toString());
				}
			}
		}
		StringBuffer returnUrl = new StringBuffer();
		returnUrl.append(req.getRequestURL());
		if (StringUtils.isNotBlank(params.toString())) {
			returnUrl.append("?").append(params.toString());
		}
		return getEncodeUrl(returnUrl.toString(), "UTF-8");
	}

	private static String getEncodeUrl(String url, String enc) {

		try {
			return URLEncoder.encode(url, enc);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static UserCache getTestUser() {
		if (init == false) {
			init();
		}

		return SsoClientUtil.testUser;
	}

	/**
	 * 设置联合登录openId
	 * 
	 * @return
	 */
	public static boolean setUnionOpenId(UserUnionLoginDTO userUnionLoginDTO,
			String ut) {
		if (init == false) {
			init();
		}
		return TicketUtils.setUnionOpenId(userUnionLoginDTO, ut, proxy);
	}

	/**
	 * 获取联合登录的openId
	 * 
	 * @param ut
	 * @return
	 */
	public static Map<String, String> getUnionOpenId(String ut) {
		if (init == false) {
			init();
		}
		return TicketUtils.getUnionOpenId(ut, proxy);
	}

	/**
	 * @param userId
	 * @description <pre>
	 * 从缓存中获取用户权限
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> getLoginUserFunction(Long userId) {
		if (init == false) {
			init();
		}
		return (Set<String>) proxy.get(userId + "");
	}

	public static void setDomain(String domain) {
		SsoClientUtil.domain = domain;
	}

}
