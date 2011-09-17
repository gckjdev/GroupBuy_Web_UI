package com.orange.groupbuy.web.client.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Criteria implements Serializable {

	private static final long serialVersionUID = -3527762787390547416L;

	private OperationType operationType = OperationType.CATEGORY_SHOW;
	private Category category;
	private OrderType orderType;
	private String city;
	private boolean onlyToday;
	private int pageSize;
	private int startRow;
	private String keyword;
	private String startPrice;
	private String endPrice;
	private ArrayList<String> categoryList;

	/**
	 * Min price, Max price
	 */
	private ArrayList<String> priceList;
	
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

	public int getPageSize() {
		return pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArrayList<String> getPriceList() {
		return priceList;
	}

	public void setPriceList(ArrayList<String> priceList) {
		this.priceList = priceList;
	}

	public String getStartPrice() {
		return startPrice;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	public ArrayList<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<String> categoryList) {
		this.categoryList = categoryList;
	}
}
