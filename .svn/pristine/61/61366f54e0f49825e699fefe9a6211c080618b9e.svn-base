package com.people2000.common.base.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContextHolder {
	private static Logger logger = LoggerFactory
			.getLogger(DataSourceContextHolder.class);
	private static final ThreadLocal<DatasourceStack> threadLocal = new ThreadLocal<DatasourceStack>();

	public static void markAsRead(DatasourcePropagation propagation) {
		DatasourceStack datasourceStack = (DatasourceStack) threadLocal.get();
		if (datasourceStack == null) {
			datasourceStack = new DatasourceStack("read");
		}
		if (DatasourcePropagation.REQUIRED.equals(propagation))
			datasourceStack.setDatasourceType(datasourceStack
					.getDatasourceType());
		else if (DatasourcePropagation.REQUIRES_NEW.equals(propagation)) {
			datasourceStack.setDatasourceType("read");
		}
		datasourceStack.push("read");
		if (logger.isDebugEnabled()) {
			logger.debug(datasourceStack.toString());
		}
		threadLocal.set(datasourceStack);
	}

	public static void markAsWrite(DatasourcePropagation propagation) {
		DatasourceStack datasourceStack = (DatasourceStack) threadLocal.get();
		if (datasourceStack == null) {
			datasourceStack = new DatasourceStack("write");
		}
		if (DatasourcePropagation.REQUIRED.equals(propagation))
			datasourceStack.setDatasourceType(datasourceStack
					.getDatasourceType());
		else if (DatasourcePropagation.REQUIRES_NEW.equals(propagation)) {
			datasourceStack.setDatasourceType("write");
		}
		datasourceStack.push("write");
		if (logger.isDebugEnabled()) {
			logger.debug(datasourceStack.toString());
		}
		threadLocal.set(datasourceStack);
	}

	public static String getDatasourceType() {
		DatasourceStack datasourceStack = (DatasourceStack) threadLocal.get();
		if (datasourceStack != null) {
			return datasourceStack.getDatasourceType();
		}
		return null;
	}

	public static void clean(DatasourcePropagation propagation) {
		DatasourceStack datasourceStack = (DatasourceStack) threadLocal.get();
		if (datasourceStack == null) {
			return;
		}
		if (datasourceStack.isEmpty()) {
			threadLocal.remove();
			return;
		}
		datasourceStack.pop();
		if (datasourceStack.isEmpty()) {
			threadLocal.remove();
		} else {
			String peek = datasourceStack.peek();
			if (DatasourcePropagation.REQUIRED.equals(propagation))
				datasourceStack.setDatasourceType(datasourceStack
						.getInitialDatasourceType());
			else if (DatasourcePropagation.REQUIRES_NEW.equals(propagation)) {
				datasourceStack.setDatasourceType(peek);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(datasourceStack.toString());
			}
			threadLocal.set(datasourceStack);
		}
	}
}
