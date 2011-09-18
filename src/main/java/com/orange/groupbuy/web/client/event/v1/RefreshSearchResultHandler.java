package com.orange.groupbuy.web.client.event.v1;

import com.google.gwt.event.shared.EventHandler;

public interface RefreshSearchResultHandler extends EventHandler {
	void onRefresh(RefreshSearchResultEvent event);
}
