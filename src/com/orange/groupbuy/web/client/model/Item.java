package com.orange.groupbuy.web.client.model;

import net.customware.gwt.dispatch.shared.Result;

public class Item implements Result {

	private String value;

	private String displayName;

	public Item() {

	}

	public Item(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
