package com.orange.groupbuy.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {

	// I added this line below
	public static final Resources INSTANCE = GWT.create(Resources.class);

	@Source("images/logo_s.gif")
	ImageResource companyTitle();

	@Source("images/sina.jpg")
	ImageResource sina();

}
