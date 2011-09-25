package com.orange.groupbuy.web.client.view;

import net.customware.gwt.presenter.client.EventBus;

import com.orange.groupbuy.web.client.component.CityWidget;
import com.orange.groupbuy.web.client.event.ResizeMainEvent;

public class TodayViewImpl extends AbstractGroupBuyView {

	public TodayViewImpl(EventBus eventBus, CityWidget citySelect) {
		super(eventBus, citySelect);
	}

	@Override
	public int getTabIndex() {
		return 1;
	}


}
