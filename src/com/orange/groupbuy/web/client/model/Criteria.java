package com.orange.groupbuy.web.client.model;

import java.io.Serializable;

public class Criteria implements Serializable {

	private static final long serialVersionUID = -3527762787390547416L;

	private Category category;
	private OrderType orderType;
	private String city;
	private boolean onlyToday;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isOnlyToday() {
		return onlyToday;
	}

	public void setOnlyToday(boolean onlyToday) {
		this.onlyToday = onlyToday;
	}
}
