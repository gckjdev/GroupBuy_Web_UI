package com.orange.groupbuy.web.client.model;

import java.util.ArrayList;
import java.util.List;

public enum OrderType {
	DATE(0, "最近"), PRICE(1, "最低价"), PRICE_RATE(2, "最划算"), POPULAR_SALE(3, "最热销"), LOCATION(
			4, "最方便");

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
		List<OrderType> result = new ArrayList<OrderType>();
		for (OrderType v : values) {
			result.add(v);
		}
		result.remove(LOCATION);
		result.remove(DATE);
		return result.toArray(new OrderType[result.size()]);
	}

	public String getIdentify(Category category) {
		String value = category.name() + "_" + name();
		return value;
	}
}
