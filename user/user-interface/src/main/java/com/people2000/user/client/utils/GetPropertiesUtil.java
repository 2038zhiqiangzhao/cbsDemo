package com.people2000.user.client.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by xiaole on 2016/3/15.
 */
public class GetPropertiesUtil {
	public static String getProperties(String path, String key) {
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration(path);
		} catch (ConfigurationException e) {
			// LogUtils.getLogger(GetPropertiesUtil.class).error(e.getMessage(),e);;
		}
		String value = config.getString(key);
		return value;
	}
}
