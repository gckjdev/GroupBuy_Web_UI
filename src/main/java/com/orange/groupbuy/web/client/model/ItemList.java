package com.orange.groupbuy.web.client.model;

import java.util.ArrayList;

import net.customware.gwt.dispatch.shared.Result;

public class ItemList implements Result {

	private ArrayList<Item> items;

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
