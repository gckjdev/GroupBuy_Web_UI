package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GroupBuyHeaderPanel extends Composite {

//	@UiField
//	ListBox citySelect;

	@UiField
	Anchor loginLink;
	
	@UiField
    Anchor logoutLink; 
	
	@UiField
	Anchor profileLink;
	
	@UiField
	Anchor registerLink;
	
	@UiField
    Anchor cityLink;
	
	@UiField
	Anchor currCity;
	
	@UiField
	TextBox searchBox;

	@UiField
	Anchor searchSubmit;

	private static GroupBuyHeaderPanelUiBinder uiBinder = GWT
			.create(GroupBuyHeaderPanelUiBinder.class);

	interface GroupBuyHeaderPanelUiBinder extends
			UiBinder<Widget, GroupBuyHeaderPanel> {
	}

	public GroupBuyHeaderPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

//	public ListBox getCitySelect() {
//		return citySelect;
//	}

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

    public Anchor getCityLink() {
        return cityLink;
    }

	public TextBox getSearchBox() {
		return searchBox;
	}

	public Anchor getSearchSubmit() {
		return searchSubmit;
	}

	public Anchor getCurrCity() {
		return currCity;
	}

	public void setCurrCity(Anchor currCity) {
		this.currCity = currCity;
	}
}
