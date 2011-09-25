package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PageListWidget extends Composite {

	private static PageListWidgetUiBinder uiBinder = GWT
			.create(PageListWidgetUiBinder.class);

	interface PageListWidgetUiBinder extends UiBinder<Widget, PageListWidget> {
	}

	private final int pageSize = 6;

	@UiField
	Anchor nextPage;

	@UiField
	Anchor previousPage;

	@UiField
	Label description;

	private int currentPage = 1;
	
	public PageListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		setCurrentPage(1);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int page) {
		currentPage = page;
		setDescription("第" + String.valueOf(getStartRow() + 1) + " - "
				+ String.valueOf(getStartRow() + pageSize) + "项");
	}

	public int nextPage() {
		int nextPage = getCurrentPage() + 1;
		setCurrentPage(nextPage);
		return nextPage;
	}

	public int previousPage() {
		int currentPage = getCurrentPage();
		if (currentPage <= 1) {
			currentPage = 1;
			setCurrentPage(currentPage);
			return currentPage;
		}

		int previousPage = getCurrentPage() - 1;
		setCurrentPage(previousPage);
		return previousPage;
	}

	public void setDescription(String text) {
		description.setText(text);
	}

	public int getStartRow() {
		return (currentPage - 1) * pageSize;
	}

	public Anchor getNextPage() {
		return nextPage;
	}

	public Anchor getPreviousPage() {
		return previousPage;
	}

	public int getPageSize() {
		return pageSize;
	}

}
