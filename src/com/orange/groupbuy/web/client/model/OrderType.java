package com.orange.groupbuy.web.client.model;

public enum OrderType {
	PRICE(0, "最低价"), PRICE_RATE(1, "最划算"), POPULAR_SALE(2, "最热销"), LOCATION(3,
			"最方便");

	private int value;
	private String displayName;

	OrderType(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public int getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static OrderType[] getDisplayOrder() {
		OrderType[] values = OrderType.values();
		return values;
	}
}
