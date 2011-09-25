package com.orange.groupbuy.web.client.view;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.user.client.ui.TextBox;
import com.orange.groupbuy.web.client.component.CityWidget;
import com.orange.groupbuy.web.client.event.ResizeMainEvent;

public class SearchViewImpl extends AbstractGroupBuyView {

	public SearchViewImpl(EventBus eventBus, CityWidget citySelect,
			TextBox searchBox) {
		super(eventBus, citySelect, searchBox);
	}

	@Override
	public int getTabIndex() {
		return 2;
	}

}
