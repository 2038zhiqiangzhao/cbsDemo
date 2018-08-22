/**
 *
 */
package com.people2000.user.client.filter;

import java.io.IOException;

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

import com.people2000.common.log.LogUtils;
import com.people2000.user.client.utils.FilterUtil;
import com.people2000.user.client.utils.RequestUtils;
import com.people2000.user.client.utils.ResponseUtil;
import com.people2000.user.client.utils.SsoClientUtil;
import com.people2000.user.model.dto.UserCache;

/**
 * 
 * 权限过滤器 全部url拦截.
 * 
 * @author crc
 * @time 2015-5-3 上午10:00:53
 * @description
 * 
 */
public class FunctionFilter implements Filter {

	private static Logger log = LogUtils.getLogger(FunctionFilter.class);

	private final static String ExcludeConfigName = "excludeURL";
	private final static String LoginUrlConfigName = "loginURL";
	private static String LoginUrl = null;
	private FunctionFilterConfig configs;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LoginUrl = filterConfig.getInitParameter(LoginUrlConfigName);
		String excludeConfig = filterConfig.getInitParameter(ExcludeConfigName);
		configs = FunctionFilterConfig.getInstance(excludeConfig);
	}

	private String getRequetUriExcludeContextPath(HttpServletRequest req) {
		String requestUrl = req.getRequestURI();// 访问全路径
		String contextPath = req.getContextPath();// 容器名称
		String reqPath = requestUrl.replace(contextPath, "");// 本次访问路径
		log.debug("requestUrl={},contextPath={},reqPath={}", requestUrl,
				contextPath, reqPath);
		return reqPath;
	}

	private UserCache getUserCache(HttpServletRequest req) {
		try {
			String ut = SsoClientUtil.getUt(req);
			if (StringUtils.isBlank(ut)) {
				return null;
			}
			UserCache userCache = SsoClientUtil.getUser(ut);
			/*
			 * if (userCache == null) { UserLoginServiceClient
			 * userLoginServiceClient = UserLoginServiceClient.getInstanst();
			 * userCache = userLoginServiceClient.getUserByLoginCookie(ut); }
			 */
			return userCache;
		} catch (Exception e) {
			log.error("getUserCache exception found", e);
			return null;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String requestUri = getRequetUriExcludeContextPath(req);// 本次访问路径

		if (!isMustAuthorized(requestUri)) {
			chain.doFilter(request, response);
			return;
		}

		UserCache userCache = getUserCache(req);
		if (userCache == null) {
			permissionDenied(req, resp);
		} else if (!userCache.getFunctionPahts().contains(requestUri)) {
			permissionDenied(req, resp);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * 是否需要权限验证
	 * 
	 * @param reqPath
	 * @return
	 */
	private boolean isMustAuthorized(String reqPath) {
		if (FilterUtil.checkFunctionPath(reqPath)) {
			return false;
		}
		if (configs.match(reqPath)) {
			return false;
		}
		return true;
	}

	private void permissionDenied(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (RequestUtils.isAjax(request)) {
			ResponseUtil.writeAjaxAuthFail(response);
		} else {
			response.sendRedirect(StringUtils.isNotBlank(LoginUrl) ? LoginUrl
					: "/");
		}
	}

	@Override
	public void destroy() {
	}

}
