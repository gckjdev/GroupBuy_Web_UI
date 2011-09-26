package com.orange.groupbuy.web.client.dispatch;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.orange.groupbuy.web.client.secure.CookiesUtil;
import com.orange.groupbuy.web.shared.UIConstatns;

public abstract class AbstractSecureAction<R extends Result> implements
		Action<R> {


	private String userId;

	public AbstractSecureAction() {
		userId = CookiesUtil.get(UIConstatns.USER_ID);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
