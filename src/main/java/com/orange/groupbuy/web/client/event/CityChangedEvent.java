package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class CityChangedEvent extends GwtEvent<CityChangedHandler> {

	private static Type<CityChangedHandler> TYPE;

	public static Type<CityChangedHandler> getType() {
		return TYPE != null ? TYPE: (TYPE = new Type<CityChangedHandler>());
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<CityChangedHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(CityChangedHandler handler) {
		handler.onChanged(this);
	}

}
