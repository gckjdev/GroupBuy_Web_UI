package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class CollpaseBox extends Composite {

	private static CollpaseBoxUiBinder uiBinder = GWT
			.create(CollpaseBoxUiBinder.class);

	interface CollpaseBoxUiBinder extends UiBinder<Widget, CollpaseBox> {
	}

	@UiField
	Label name;

	@UiField
	LayoutPanel content;

	public CollpaseBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Label getName() {
		return name;
	}

	public LayoutPanel getContent() {
		return content;
	}

	@SuppressWarnings("unchecked")
	public <T> CellTable<T> getContentCellTable() {
		return (CellTable<T>) content.getWidget(0);
	}
}
