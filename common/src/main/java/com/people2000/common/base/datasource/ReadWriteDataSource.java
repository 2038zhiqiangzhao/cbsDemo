package com.people2000.common.base.datasource;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ReadWriteDataSource extends AbstractRoutingDataSource {
	private int readDataSourceCount;
	private AtomicLong totalCount = new AtomicLong(0L);

	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		this.readDataSourceCount = (targetDataSources.size() - 1);
	}

	public ReadWriteDataSource() {
	}

	public ReadWriteDataSource(int readDataSourceCount) {
		this.readDataSourceCount = readDataSourceCount;
	}

	protected Object determineCurrentLookupKey() {
		String datasourceType = DataSourceContextHolder.getDatasourceType();
		if (datasourceType == null) {
			return "default";
		}
		if (datasourceType.equalsIgnoreCase("write")) {
			return datasourceType;
		}
		if (this.readDataSourceCount == 0) {
			return datasourceType;
		}
		long i = this.totalCount.getAndAdd(1L);
		long j = i % this.readDataSourceCount;
		if (j == 0L) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("datasource key is:" + datasourceType);
			}
			return datasourceType;
		}
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("datasource key is:" + datasourceType + j);
		}
		return datasourceType + j;
	}
}
