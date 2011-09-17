package com.orange.groupbuy.web.client.model;

public enum OperationType {
	KEYWORD_SEARCH("fpk"), CATEGORY_SHOW("fp"), MY_GROUP("");

	private String methodName;

	OperationType(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}
}
