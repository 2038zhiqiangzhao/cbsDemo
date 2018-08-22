package com.people2000.user.business.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author wangyangsong
 *
 */
public class PropertyUtils {
	public static Properties getProperty(String URL) {
		Properties properties = new Properties();
		String path = System.getProperty("global.config.path") + URL;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(path);
			InputStreamReader is = new InputStreamReader(fileInputStream,
					"UTF-8");
			properties.load(is);
			return properties;
		} catch (Exception e) {
			// throw new RuntimeException( path + " is not exist.");
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				LogFactory.getLog(PropertyUtils.class).error(e.getMessage(), e);
				;
			}
		}
	}
}
