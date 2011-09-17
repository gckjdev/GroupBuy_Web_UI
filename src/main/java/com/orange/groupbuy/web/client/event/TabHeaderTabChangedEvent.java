package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class TabHeaderTabChangedEvent extends
		GwtEvent<TabHeaderTabChangedHandler> {

	private static Type<TabHeaderTabChangedHandler> TYPE;

	public static Type<TabHeaderTabChangedHandler> getType() {
		return TYPE != null ? TYPE
				: (TYPE = new Type<TabHeaderTabChangedHandler>());
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TabHeaderTabChangedHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(TabHeaderTabChangedHandler handler) {
		handler.onChanged(this);
	}

}
