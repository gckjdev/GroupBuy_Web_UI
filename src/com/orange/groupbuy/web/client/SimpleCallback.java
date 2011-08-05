package com.orange.groupbuy.web.client;

import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class SimpleCallback<T extends Result> implements
		AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		// TODO:
		Window.alert("Error: " + caught.getMessage());
	}


}
