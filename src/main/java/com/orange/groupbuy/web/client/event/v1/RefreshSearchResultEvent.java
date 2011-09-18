package com.orange.groupbuy.web.client.event.v1;

import com.google.gwt.event.shared.GwtEvent;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.OrderType;

public class RefreshSearchResultEvent extends
		GwtEvent<RefreshSearchResultHandler> {

	private static Type<RefreshSearchResultHandler> TYPE;

	private final int pageSize = 30;
	private String city;
	private Category category;
	private OrderType orderType;
	private boolean onlyToday = false;
	private int currentPage = 1;

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

	public boolean isOnlyToday() {
		return onlyToday;
	}

	public void setOnlyToday(boolean onlyToday) {
		this.onlyToday = onlyToday;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartRow() {
		return (currentPage - 1) * pageSize;
	}
}
