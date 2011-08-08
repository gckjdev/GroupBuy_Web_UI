package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PageListWidget extends Composite {

	private static PageListWidgetUiBinder uiBinder = GWT
			.create(PageListWidgetUiBinder.class);

	interface PageListWidgetUiBinder extends UiBinder<Widget, PageListWidget> {
	}

	@UiField
	Button nextPage;

	@UiField
	Button previousPage;

	@UiField
	Label currentPage;

	public PageListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public int getCurrentPage() {
		return Integer.valueOf(currentPage.getText());
	}

	public void setCurrentPage(int page) {
		currentPage.setText(String.valueOf(page));
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

	public Button getNextPage() {
		return nextPage;
	}

	public Button getPreviousPage() {
		return previousPage;
	}

}
