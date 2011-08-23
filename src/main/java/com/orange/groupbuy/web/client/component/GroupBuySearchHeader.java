package com.orange.groupbuy.web.client.component;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.orange.groupbuy.web.client.Resources;

public class GroupBuySearchHeader extends Composite {

	private Image titleImage;
	private ListBox citySeclect;
	private TextBox searchTextBox;
	private Button searchButton;

	public ListBox getCitySeclect() {
		return citySeclect;
	}

	public TextBox getSearchTextBox() {
		return searchTextBox;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public GroupBuySearchHeader() {

		HorizontalPanel compositePanel = new HorizontalPanel();

		titleImage = new Image(Resources.INSTANCE.companyTitle());
		compositePanel.add(titleImage);

		// search area
		compositePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		compositePanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		HorizontalPanel searchArea = new HorizontalPanel();
		searchArea.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		compositePanel.add(searchArea);
		searchTextBox = new TextBox();
		searchTextBox.setStyleName("searchInputBox");
		searchArea.add(searchTextBox);
		searchButton = new Button("搜一搜");
		searchArea.add(searchButton);
		//
		citySeclect = new ListBox();
		compositePanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		compositePanel.add(citySeclect);
		initWidget(compositePanel);
	}

	public void updateModel() {
		citySeclect.addItem("å¹¿å·ž", "GZ");
		citySeclect.addItem("åŒ—äº¬", "BJ");
	}
}
