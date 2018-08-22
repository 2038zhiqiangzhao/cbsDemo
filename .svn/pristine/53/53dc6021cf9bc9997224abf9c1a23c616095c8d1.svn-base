package com.people2000.user.business.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author crc
 * @time 2015-5-29 下午5:06:58
 * @description <pre>
 * 
 * </pre>
 *
 */
public final class ResponseUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(ResponseUtil.class);

	public static String writeAjaxLoginFail(HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "99");
		jsonObject.put("message", "没有登陆或者登陆超时！");
		response.getWriter().write(jsonObject.toString());
		response.getWriter().close();
		return null;
	}

	public static String writeAjaxExceptionFail(HttpServletResponse response,
			String message) throws IOException {

		response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "100");
		jsonObject.put("message", message);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(jsonObject.toString());
		response.getWriter().close();
		return null;
	}

	public static String writeAjaxAuthFail(HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", "98");
		jsonObject.put("message", "无权限进行此操作！");
		response.getWriter().write(jsonObject.toString());
		response.getWriter().close();
		return null;
	}

	public static String writeAjaxNoRight(HttpServletResponse response)
			throws IOException {
		JSONObject jObject = new JSONObject();
		jObject.put("success", false);
		jObject.put("message", "没有权限");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jObject.toString());
		response.getWriter().close();
		return null;
	}

	public static String writeFail(HttpServletResponse response, String msg) {
		try {
			JSONObject jObject = new JSONObject();
			jObject.put("success", false);
			jObject.put("message", StringUtils.isEmpty(msg) ? "没有权限" : msg);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jObject.toString());
			response.getWriter().close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
}
