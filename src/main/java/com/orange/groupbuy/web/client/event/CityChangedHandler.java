package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface CityChangedHandler extends EventHandler {
	void onChanged(CityChangedEvent event);
}