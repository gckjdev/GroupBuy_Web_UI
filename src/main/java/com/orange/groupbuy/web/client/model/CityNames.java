package com.orange.groupbuy.web.client.model;

import java.util.ArrayList;

import net.customware.gwt.dispatch.shared.Result;

public class CityNames implements Result {

	private ArrayList<Item> cityList;

	public ArrayList<Item> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<Item> cityList) {
		this.cityList = cityList;
	}

}
