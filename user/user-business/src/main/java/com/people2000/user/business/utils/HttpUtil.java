package com.people2000.user.business.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

import com.people2000.common.log.LogUtils;

public class HttpUtil {

	private static Logger logger = Logger.getLogger(HttpUtil.class);

	public static Object request(String requestURI, String requestMethod,
			Map<String, String> parameterMap) {
		System.out.println("===============HttpUtil.request ==============");
		// 处理业务逻辑
		try {
			Connection conn = HttpConnection.connect(requestURI);
			conn.timeout(10000);
			conn.header("Accept-Encoding", "gzip,deflate");
			conn.header("Connection", "close");
			if ("get".equalsIgnoreCase(requestMethod)) {
				conn.request().method(Connection.Method.GET);
			} else if ("post".equalsIgnoreCase(requestMethod)) {
				conn.request().method(Connection.Method.POST);
			}
			if (parameterMap != null && !parameterMap.isEmpty()) {
				System.out.println("httpMap : " + parameterMap);
				conn.data(parameterMap);
			}
			conn.ignoreContentType(true);
			Connection.Response response = conn.execute();
			System.out
					.println("===============HttpUtil.request ==============");
			if (response.statusCode() == 200) {
				// 是否以"Exception:"开头
				String body = response.body();
				if (body.startsWith("Exception:")) {
					throw new RuntimeException(body);
				}
				return body;
			} else {
				throw new RuntimeException("请求内部api报错!" + requestURI);
			}

		} catch (Exception e) {
			// LogUtils.error(HttpUtil.class, e);
			LogUtils.getLogger(HttpUtil.class).error(e.getMessage(), e);
			;
		}
		return null;
	}

	public static String HttpPOSTWithJson(String url, String json) {
		StringBuffer sb = new StringBuffer("");
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("调用地址:" + url);
				logger.debug("入参:" + json);
			}
			// 创建连接
			URL requestURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) requestURL
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(URLEncoder.encode(json, "utf-8"));
			out.flush();
			out.close();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;

			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			// System.out.println(sb);
			reader.close();
			// 断开连接
			connection.disconnect();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(HttpUtil.class).error(e.getMessage(), e);
			;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(HttpUtil.class).error(e.getMessage(), e);
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogUtils.getLogger(HttpUtil.class).error(e.getMessage(), e);
			;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(sb.toString());
		}
		return sb.toString();

	}
}
