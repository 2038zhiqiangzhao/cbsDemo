package com.people2000.common.base.datasource;

import java.io.Serializable;
import java.util.Stack;

public class DatasourceStack implements Serializable {
	/**
	 */
	private static final long serialVersionUID = 8481761116403495753L;
	private final String initialDatasourceType;
	private String datasourceType;
	private Stack<String> stack;

	public DatasourceStack(String initialDatasourceType) {
		this.initialDatasourceType = initialDatasourceType;
		this.datasourceType = initialDatasourceType;
		this.stack = new Stack<String>();
	}

	public void push(String string) {
		this.stack.push(string);
	}

	public String pop() {
		return (String) this.stack.pop();
	}

	public boolean isEmpty() {
		return this.stack.isEmpty();
	}

	public String peek() {
		return (String) this.stack.peek();
	}

	public String getDatasourceType() {
		return this.datasourceType;
	}

	public void setDatasourceType(String datasourceType) {
		this.datasourceType = datasourceType;
	}

	public Stack<String> getStack() {
		return this.stack;
	}

	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}

	public String getInitialDatasourceType() {
		return this.initialDatasourceType;
	}

	public String toString() {
		return "DatasourceStack{datasourceType='" + this.datasourceType + '\''
				+ ", stack=" + this.stack + '}';
	}
}
