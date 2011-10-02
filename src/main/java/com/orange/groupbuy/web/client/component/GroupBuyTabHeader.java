package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class GroupBuyTabHeader extends Composite {

	@UiField
	TabLayoutPanel tabHeader;

	// @UiField
	// LayoutPanel myGroupView;

	@UiField
	LayoutPanel sortView;

	@UiField
	LayoutPanel todayView;
	
	@UiField
    HTMLPanel iphoneView ;

	private static GroupBuyTabHeaderUiBinder uiBinder = GWT
			.create(GroupBuyTabHeaderUiBinder.class);

	interface GroupBuyTabHeaderUiBinder extends
			UiBinder<Widget, GroupBuyTabHeader> {
	}

	public GroupBuyTabHeader() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	// public LayoutPanel getMyGroupView() {
	// return myGroupView;
	// }

	public LayoutPanel getSortView() {
		return sortView;
	}

	public LayoutPanel getTodayView() {
		return todayView;
	}
	
	public HTMLPanel getIPhoneView() {
        return iphoneView;
    }

	public TabLayoutPanel getTabHeader() {
		return tabHeader;
	}

}
