package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class GroupBuyHeaderPanel extends Composite {

	@UiField
	ListBox citySelect;

	@UiField
	Anchor loginLink;
	
	@UiField
    Anchor logoutLink;
	
	@UiField
	Anchor profileLink;
	
	@UiField
    Anchor registerLink;
	
	private static GroupBuyHeaderPanelUiBinder uiBinder = GWT
			.create(GroupBuyHeaderPanelUiBinder.class);

	interface GroupBuyHeaderPanelUiBinder extends
			UiBinder<Widget, GroupBuyHeaderPanel> {
	}

	public GroupBuyHeaderPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ListBox getCitySelect() {
		return citySelect;
	}

    public Anchor getLoginLink() {
        return loginLink;
    }
    
    public Anchor getLogoutLink() {
        return logoutLink;
    }
    
    public Anchor getProfileLink() {
        return profileLink;
    }
    
    public Anchor getRegisterLink() {
        return registerLink;
    }

}
