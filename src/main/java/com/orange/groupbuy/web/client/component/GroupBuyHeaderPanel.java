package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GroupBuyHeaderPanel extends Composite {

	private static GroupBuyHeaderPanelUiBinder uiBinder = GWT
			.create(GroupBuyHeaderPanelUiBinder.class);

	interface GroupBuyHeaderPanelUiBinder extends
			UiBinder<Widget, GroupBuyHeaderPanel> {
	}

	public GroupBuyHeaderPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
