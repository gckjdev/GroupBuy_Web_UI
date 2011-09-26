package com.orange.groupbuy.web.server.handler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.orange.groupbuy.web.client.dispatch.AbstractSecureAction;
import com.orange.groupbuy.web.client.model.SecureResult;
import com.orange.groupbuy.web.client.model.UserInfo;
import com.orange.groupbuy.web.server.util.SessionUtil;

public abstract class AbstractSecureActionHandler<A extends AbstractSecureAction<R>, R extends SecureResult>
		implements ActionHandler<A, R> {

	@Override
	public R execute(A action, ExecutionContext executioncontext)
			throws DispatchException {
		R result = executeIntenal(action, executioncontext);
		// is login check
		boolean isValid = isValidLogin(action);
		result.setValid(isValid);
		return result;
	}

	protected boolean isValidLogin(A action) {
		AbstractSecureAction<R> secureAction = action;
		boolean isValid = true;
		UserInfo userInfo = (UserInfo) SessionUtil.get().getAttribute(
				secureAction.getUserId());
		if (userInfo == null) {
			isValid = false;
		}
		return isValid;
	}

	protected abstract R executeIntenal(A action,
			ExecutionContext executioncontext);
}
