package com.orange.groupbuy.web.client.component;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.orange.groupbuy.web.client.Resources;

public class GroupBuySearchHeader extends Composite {

	private Image titleImage;
	private ListBox citySeclect;

	public ListBox getCitySeclect() {
		return citySeclect;
	}

	public GroupBuySearchHeader() {

		HorizontalPanel compositePanel = new HorizontalPanel();

		titleImage = new Image(Resources.INSTANCE.companyTitle());
		compositePanel.add(titleImage);

		citySeclect = new ListBox();
		compositePanel.setSpacing(5);
		compositePanel.add(citySeclect);
		initWidget(compositePanel);

	}

	public void updateModel() {
		citySeclect.addItem("å¹¿å·ž", "GZ");
		citySeclect.addItem("åŒ—äº¬", "BJ");
	}
}
