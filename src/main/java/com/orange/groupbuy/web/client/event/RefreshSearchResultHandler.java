package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface RefreshSearchResultHandler extends EventHandler {
	void onRefresh(RefreshSearchResultEvent event);
}
