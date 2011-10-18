package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ResizeMainEvent extends GwtEvent<ResizeMainHandler>{
	private int height;
	
	public ResizeMainEvent(int h){
		super();
		height = h;
	}
	public int getHeight(){
		return height;
	}
	
	private static Type<ResizeMainHandler> TYPE;

	public static Type<ResizeMainHandler> getType() {
		return TYPE != null ? TYPE
				: (TYPE = new Type<ResizeMainHandler>());
	}

	@Override
	protected void dispatch(ResizeMainHandler hander) {
		hander.resize(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ResizeMainHandler> getAssociatedType() {
		return getType();
	}
}
