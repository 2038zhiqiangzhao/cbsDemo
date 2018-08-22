package com.people2000.common.base.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceAop {
	private Logger log = LoggerFactory.getLogger(DataSourceAop.class);
	private DatasourcePropagation propagation = DatasourcePropagation.REQUIRED;

	public void beforeReadMethod(JoinPoint joinPoint) {
		if (this.log.isDebugEnabled()) {
			StringBuilder builder = new StringBuilder();

			Signature signature = joinPoint.getSignature();

			String methodName = signature.getName();

			String className = joinPoint.getTarget().getClass().getName();

			builder.append("Class:");
			builder.append(className);
			builder.append(" ");
			builder.append("Method:");
			builder.append(methodName);
			this.log.debug(builder.toString() + " mark as use ReadDataSource");
		}
		DataSourceContextHolder.markAsRead(this.propagation);
	}

	public void beforeWriteMethod(JoinPoint joinPoint) {
		if (this.log.isDebugEnabled()) {
			StringBuilder builder = new StringBuilder();

			Signature signature = joinPoint.getSignature();

			String methodName = signature.getName();

			String className = joinPoint.getTarget().getClass().getName();

			builder.append("Class:");
			builder.append(className);
			builder.append(" ");
			builder.append("Method:");
			builder.append(methodName);
			this.log.debug(builder.toString() + " mark as use WriteDataSource");
		}
		DataSourceContextHolder.markAsWrite(this.propagation);
	}

	public void afterExecution() {
		DataSourceContextHolder.clean(this.propagation);
	}

	public DatasourcePropagation getPropagation() {
		return this.propagation;
	}

	public void setPropagation(DatasourcePropagation propagation) {
		this.propagation = propagation;
	}
}
