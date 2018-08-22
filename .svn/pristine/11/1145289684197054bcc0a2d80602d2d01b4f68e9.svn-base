package com.people2000.user.web.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.people2000.user.model.vo.MobileUserVO;

/**
 * Created with IntelliJ IDEA.
 *
 * @time: 2016/9/8 10:15
 * @author: Wang Pengfei
 * @description:
 * @version:
 */
public class HeaderUtils {
	public static Map<String, String> getHeader(HttpServletRequest req) {
		Map<String, String> map = new HashMap();
		Enumeration<String> e = req.getHeaderNames();

		while (e.hasMoreElements()) {
			String name = e.nextElement();
			map.put(name, req.getHeader(name));
		}

		return map;
	}

	/**
	 * 把头信息放入user
	 *
	 * @param req
	 * @param user
	 * @return
	 */
	public static MobileUserVO fillInDeviceId(HttpServletRequest req,
			MobileUserVO user) {
		// FIXME 暂时没想到 好主意. 此处需要优化.
		Map<String, String> map = getHeader(req);
		if (map != null) {
			if (map.get("deviceId") != null) {
				user.setDeviceId(map.get("deviceId"));
			}
			if (map.get("deviceid") != null) {
				user.setDeviceId(map.get("deviceid"));
			}
		}
		return user;
	}

	public static String getDeviceId(HttpServletRequest req) {
		try {
			Map<String, String> map = getHeader(req);
			if (map != null) {
				if (map.get("deviceId") != null) {
					return map.get("deviceId");
				}
				if (map.get("deviceid") != null) {
					return map.get("deviceid");
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
