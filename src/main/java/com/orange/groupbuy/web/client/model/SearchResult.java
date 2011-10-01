package com.orange.groupbuy.web.client.model;

import net.customware.gwt.dispatch.shared.Result;

public class SearchResult implements Result {

	// id
	private String detailsId;
	// site info
	private String siteName;
	private String siteUrl;
	// group buy product
	private String productUrl;
	private String imageUrl;
	// private String imageClickUrl;

	private String category;
	private String desctiption;

	// sale status
	// original price
	private Double price;
	// current price
	private Double value;
	private Integer bought;
	private Double rebate;

	private String startDate;
	private String endDate;

	public String getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imaage) {
		this.imageUrl = imaage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// public String getTitle() {
	// return title;
	// }

	// public void setTitle(String title) {
	// this.title = title;
	// }

	public String getDesctiption() {
		return desctiption;
	}

	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getBought() {
		return bought;
	}

	public void setBought(Integer bought) {
		this.bought = bought;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
