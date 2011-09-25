package com.orange.groupbuy.web.client.view;

import net.customware.gwt.presenter.client.EventBus;

import com.orange.groupbuy.web.client.component.CityWidget;
import com.orange.groupbuy.web.client.event.ResizeMainEvent;

public class TopViewImpl extends AbstractGroupBuyView {

	public TopViewImpl(EventBus eventBus, CityWidget citySelect) {
		super(eventBus, citySelect);
	}

	@Override
	public int getTabIndex() {
		return 0;
	}

}
