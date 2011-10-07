package com.orange.groupbuy.web.client.view;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.user.client.ui.TextBox;
import com.orange.groupbuy.web.client.component.CityWidget;

public class SearchViewImpl extends AbstractGroupBuyView {

	public SearchViewImpl(EventBus eventBus, CityWidget citySelect,
			TextBox searchBox) {
		super(eventBus, citySelect, searchBox);
	}

}
