package com.orange.groupbuy.web.server;

import javax.servlet.http.HttpSession;

import net.customware.gwt.dispatch.client.standard.StandardDispatchService;
import net.customware.gwt.dispatch.server.DefaultActionHandlerRegistry;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.server.InstanceActionHandlerRegistry;
import net.customware.gwt.dispatch.server.SimpleDispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.orange.groupbuy.web.server.handler.GetCityNamesHandler;
import com.orange.groupbuy.web.server.handler.GetGroupBuyCategoryListHandler;
import com.orange.groupbuy.web.server.handler.GetMyGroupHandler;

@SuppressWarnings("serial")
public class SimpleDispatchServlet extends RemoteServiceServlet implements
		StandardDispatchService {

	private Dispatch dispatch;

	public SimpleDispatchServlet() {
		InstanceActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
		registry.addHandler(new GetCityNamesHandler());
		registry.addHandler(new GetGroupBuyCategoryListHandler());
		registry.addHandler(new GetMyGroupHandler());
		dispatch = new SimpleDispatch(registry);
	}

	private HttpSession getSession() {
		// Get the current request and then return its session
		return this.getThreadLocalRequest().getSession();
	}

	@Override
	public Result execute(Action<?> action) throws DispatchException {

		try {
			return dispatch.execute(action);
		} catch (RuntimeException e) {
			log("Exception while executing " + action.getClass().getName()
					+ ": " + e.getMessage(), e);
			throw e;
		}
	}

}