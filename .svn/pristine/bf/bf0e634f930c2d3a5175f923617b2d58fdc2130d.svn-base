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

import com.people2000.common.log.LogUtils;
import com.people2000.user.client.utils.RequestUtils;
import com.people2000.user.client.utils.ResponseUtil;

/**
 * @author crc
 * @time 2015-5-3 上午10:00:53
 * @description <pre>
 * 异常拦截:如果是ajax提交，返回错误代码为100，系统错误，如果非ajax提交，跳转到500.jsp页面
 * </pre>
 * 
 */
public class ExceptionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(e.getMessage(), e);
			doFailFilter(req, resp, e.getMessage());
		}
	}

	private void doFailFilter(HttpServletRequest request,
			HttpServletResponse response, String message) throws IOException {

		if (RequestUtils.isAjax(request)) {
			ResponseUtil.writeAjaxExceptionFail(response, message);
		} else {
			response.sendRedirect(request.getContextPath() + "/500.jsp");
		}
	}

	@Override
	public void destroy() {
	}

}
