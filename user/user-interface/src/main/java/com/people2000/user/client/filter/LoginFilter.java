/**
 *
 */
package com.people2000.user.client.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.people2000.common.http.HttpClientUtils;
import com.people2000.common.log.LogUtils;
import com.people2000.common.session.SystemContext;
import com.people2000.user.client.utils.FilterUtil;
import com.people2000.user.client.utils.RequestUtils;
import com.people2000.user.client.utils.ResponseUtil;
import com.people2000.user.client.utils.SessionContainer;
import com.people2000.user.client.utils.SsoClientUtil;
import com.people2000.user.model.dto.UserCache;

/**
 * <pre>
 * 登陆拦截，负责设置和清除SessionContainer.session，如果必须要登录的链接没有登录的话ajax返回错误码、正常请求则跳转。
 * 通过init-param支持两个配置项:
 * 1、LoginUrlConfigName指定的param-name对应的值，用于设置必须登录而未登录时的跳转地址
 * 2、ExcludeUrlConfigName指定的param-name对应的值,设置可以不登录访问的地址，多个地址以","隔开， 对应设置值可能的格式有两种：
 *   a)、精确匹配，解析后存储在excludeUrls中
 *   b)、带*号的前缀匹配,解析后存储在excludePrefixs中，<b>*后面的字符将会被忽略，只有url是以*后之前的字符开头即视为匹配</b>
 *   举个栗子：/abc,/de*，意思是不包含context path的request uri为/abc或者/de开头的话可以不需要登录即可访问
 * </pre>
 * 
 * @author crc
 * @time 2015-4-30 下午5:42:09
 * @description
 * 
 */
public class LoginFilter implements Filter {

	private static final Logger logger = LogUtils.getLogger(LoginFilter.class);

	private static final String LoginUrlConfigName = "loginURL";
	private static String loginUrl;

	private static final String ExcludeUrlConfigName = "excludeURL";
	private static Set<String> excludeUrls = new HashSet<String>();
	private static List<String> excludePrefixs = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loginUrl = filterConfig.getInitParameter(LoginUrlConfigName);
		String excludeURL = filterConfig.getInitParameter(ExcludeUrlConfigName);
		if (StringUtils.isNotBlank(excludeURL)) {
			excludeURL = excludeURL.trim();
			for (String url : excludeURL.split("\\s*,\\s*")) {
				if (url.contains("*")) {
					excludePrefixs.add(url.substring(0, url.indexOf("*")));
				} else {
					excludeUrls.add(url);
				}
			}
		}
	}

	private String getRequetUriExcludeContextPath(HttpServletRequest req) {
		String requestUrl = req.getRequestURI();// 访问全路径
		String contextPath = req.getContextPath();// 容器名称
		String reqPath = requestUrl.replace(contextPath, "");// 本次访问路径
		logger.debug("requestUrl={},contextPath={},reqPath={}", requestUrl,
				contextPath, reqPath);
		return reqPath;
	}

	/**
	 * 是否只有登录后才能访问
	 * 
	 * @param uriExcludeContextPath
	 * @return
	 */
	private boolean isMustLogin(HttpServletRequest req) {

		String uriExcludeContextPath = getRequetUriExcludeContextPath(req);

		if (FilterUtil.checkLoginPath(uriExcludeContextPath)) {
			return false;
		}
		if (excludeUrls.contains(uriExcludeContextPath)) {
			return false;
		}
		for (String prefix : excludePrefixs) {
			if (uriExcludeContextPath.startsWith(prefix)) {
				return false;
			}
		}
		return true;
	}

	private UserCache getUserCache(HttpServletRequest req) {
		String ut = SsoClientUtil.getUt(req);
		// UserCache userCache = SsoClientUtil.getUser(ut);
		// 先从本地线程变量中取出
		UserCache userCache = SessionContainer.getSession();
		if (userCache == null) {
			try {
				// 远程调取，方式1 restful接口
				String url = req.getScheme() + "://" + req.getServerName()
						+ ":" + req.getServerPort()
						+ "/user-web/login/getUserCache.do?ut=" + ut;
				String rst = HttpClientUtils.get(url);
				userCache = JSONObject.parseObject(rst, UserCache.class);

				// 远程调取，方式2 dubbo服务接口
				/*
				 * UserService userService = SpringUtils.getBean("userService");
				 * userCache = userService.getUserCache(ut);
				 */
			} catch (Exception e) {
				logger.error("remote service connect error userService"
						+ e.toString());
			}

		}
		return userCache;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		req.setCharacterEncoding(FilterUtil.UTF_8);
		resp.setCharacterEncoding(FilterUtil.UTF_8);

		UserCache userCache = getUserCache(req);
		SessionContainer.setSession(userCache);
		try {
			if (userCache != null) {
				SystemContext.setUserId(userCache.getId());
				SystemContext.setUserName(userCache.getUsername());
			}

			if (isMustLogin(req) && userCache == null) {
				doFailFilter(req, resp);
			} else {
				chain.doFilter(request, response);
			}
		} finally {
			SessionContainer.clear();
			SystemContext.clean();
		}
	}

	private void doFailFilter(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (RequestUtils.isAjax(request)) {
			ResponseUtil.writeAjaxLoginFail(response);
		} else {
			if (loginUrl != null && !"".equals(loginUrl)) {
				response.sendRedirect(loginUrl);
			} else {
				response.sendRedirect("/");
			}
		}
	}

	@Override
	public void destroy() {
	}

}
