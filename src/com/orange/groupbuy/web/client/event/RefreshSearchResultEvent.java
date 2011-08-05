package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RefreshSearchResultEvent extends
		GwtEvent<RefreshSearchResultHandler> {

	private static Type<RefreshSearchResultHandler> TYPE;

	private String city;
	private String category;
	private String orderType;
	private boolean onlyToday;

	public static Type<RefreshSearchResultHandler> getType() {
		return TYPE != null ? TYPE
				: (TYPE = new Type<RefreshSearchResultHandler>());
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RefreshSearchResultHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(RefreshSearchResultHandler handler) {
		handler.onRefresh(this);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public boolean isOnlyToday() {
		return onlyToday;
	}

	public void setOnlyToday(boolean onlyToday) {
		this.onlyToday = onlyToday;
	}

}
