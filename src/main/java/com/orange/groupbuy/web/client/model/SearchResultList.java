package com.orange.groupbuy.web.client.model;

import java.util.ArrayList;

import net.customware.gwt.dispatch.shared.Result;

public class SearchResultList implements Result {

	private ArrayList<SearchResult> results;

	public ArrayList<SearchResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<SearchResult> results) {
		this.results = results;
	}

}
