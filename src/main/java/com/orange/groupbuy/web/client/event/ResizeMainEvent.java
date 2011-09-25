package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author kuangf
 * event to resize main window according to the tab row number
 */
public class ResizeMainEvent extends GwtEvent<ResizeMainHandler>{
	private int height;
	private int tabIndex;
	
	public ResizeMainEvent(int h, int tabIndex){
		super();
		height = h;
		this.tabIndex = tabIndex;
	}
	public ResizeMainEvent(){
		super();
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
	public int getTabIndex() {
		return tabIndex;
	}
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
}
