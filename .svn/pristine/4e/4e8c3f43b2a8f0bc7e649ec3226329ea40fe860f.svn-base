package com.people2000.common.base.mybatis.plugin;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractInterceptor implements Interceptor {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected Set<String> ignoreSet;
	protected boolean enabled = false;

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		String ignoreIds = properties.getProperty("ignoreIds");
		_setIgnoreIds(ignoreIds);
		String effective = properties.getProperty("enabled");
		if (StringUtils.isNotBlank(effective))
			this.enabled = Boolean.valueOf(effective.trim()).booleanValue();
	}

	public void setIgnoreIds(String ignoreIds) {
		_setIgnoreIds(ignoreIds);
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private void _setIgnoreIds(String ignoreIds) {
		if (StringUtils.isBlank(ignoreIds)) {
			return;
		}
		if (this.ignoreSet == null) {
			this.ignoreSet = new HashSet();
		}
		StringTokenizer tokenizer = new StringTokenizer(ignoreIds, ",", false);
		while (tokenizer.hasMoreTokens())
			this.ignoreSet.add(tokenizer.nextToken().trim());
	}
}