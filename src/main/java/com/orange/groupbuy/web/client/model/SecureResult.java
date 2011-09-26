package com.orange.groupbuy.web.client.model;

import net.customware.gwt.dispatch.shared.Result;

public class SecureResult implements Result {

	public boolean valid = false;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
