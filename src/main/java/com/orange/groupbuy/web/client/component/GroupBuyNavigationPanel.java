package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GroupBuyNavigationPanel extends Composite {

	private static GroupBuyNavigationPanelUiBinder uiBinder = GWT
			.create(GroupBuyNavigationPanelUiBinder.class);

	interface GroupBuyNavigationPanelUiBinder extends
			UiBinder<Widget, GroupBuyNavigationPanel> {
	}

	public GroupBuyNavigationPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
