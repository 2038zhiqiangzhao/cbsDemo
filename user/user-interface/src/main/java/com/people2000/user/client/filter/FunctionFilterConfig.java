package com.people2000.user.client.filter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class FunctionFilterConfig {

	private final String condition;
	private final Set<String> excludeUri;
	private final Set<String> excludeUriPrefix;

	private FunctionFilterConfig(String condition, Set<String> excludeUri,
			Set<String> excludeUriPrefix) {
		this.condition = condition;
		this.excludeUri = excludeUri;
		this.excludeUriPrefix = excludeUriPrefix;
	}

	public String getCondition() {
		return condition;
	}

	public boolean match(String uri) {
		if ("development".equals(condition)) {
			return true;
		}
		if (excludeUri.contains(uri)) {
			return true;
		}
		for (String prefix : excludeUriPrefix) {
			if (uri.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	public static FunctionFilterConfig getInstance(String excludeConfiguration) {
		String condition = loadCondition();
		Set<String> excludeUriPrefix = new HashSet<String>();
		Set<String> excludeUri = new HashSet<String>();
		parseExcludeConfigurations(excludeConfiguration, excludeUriPrefix,
				excludeUri);
		return new FunctionFilterConfig(condition, excludeUri, excludeUriPrefix);
	}

	private static String loadCondition() {

		File clientProps = new File(System.getProperty("global.config.path"),
				"/ouser/ouser-client/client.properties");

		try (InputStream is = new BufferedInputStream(new FileInputStream(
				clientProps))) {
			Properties properties = new Properties();
			properties.load(is);
			Object o = properties.get("user.condition");
			return o == null ? null : o.toString();
		} catch (Exception e) {
			// throw new RuntimeException("client.properties is not exist.");
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static void parseExcludeConfigurations(String configs,
			Set<String> urls, Set<String> prefixs) {
		if (StringUtils.isNotBlank(configs)) {
			for (String url : configs.trim().split("\\s*,\\s*")) {
				if (url.contains("*")) {
					prefixs.add(url.substring(0, url.indexOf("*")));
				} else {
					urls.add(url);
				}
			}
		}
	}

}
