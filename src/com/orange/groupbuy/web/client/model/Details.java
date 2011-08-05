package com.orange.groupbuy.web.client.model;

import java.util.ArrayList;
import java.util.Date;

import net.customware.gwt.dispatch.shared.Result;

public class Details implements Result {

	//product
	private SearchResult searchResult;
	// 团购开始时间
	private Date start_date;
	// 团购结束时间
	private Date end_date;
	// 团购使用开始时间
	private Date spend_start_date;
	// 团购使用结束时间
	private Date spend_end_date;

	// vendor
	private String vendor;
	private String vendorName;
	private ArrayList<Address> addressList;

	public SearchResult getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getSpend_start_date() {
		return spend_start_date;
	}

	public void setSpend_start_date(Date spend_start_date) {
		this.spend_start_date = spend_start_date;
	}

	public Date getSpend_end_date() {
		return spend_end_date;
	}

	public void setSpend_end_date(Date spend_end_date) {
		this.spend_end_date = spend_end_date;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public ArrayList<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(ArrayList<Address> addressList) {
		this.addressList = addressList;
	}
}
