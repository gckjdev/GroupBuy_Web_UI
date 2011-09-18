package com.orange.groupbuy.web.client.event.v1;

import com.google.gwt.event.shared.GwtEvent;

public class KeywordSearchEvent extends GwtEvent<KeywordSearchHandler> {

	private static Type<KeywordSearchHandler> TYPE;
	private boolean onlyToday = false;
	private int currentPage = 1;
	private String keyword;
	private final int pageSize = 30;
	private String city;

	public static Type<KeywordSearchHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<KeywordSearchHandler>());
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<KeywordSearchHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(KeywordSearchHandler handler) {
		handler.onRefresh(this);
	}

	public boolean isOnlyToday() {
		return onlyToday;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public String getKeyword() {
		return keyword;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getCity() {
		return city;
	}

	public void setOnlyToday(boolean onlyToday) {
		this.onlyToday = onlyToday;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStartRow() {
		return (currentPage - 1) * pageSize;
	}
}
