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
import com.orange.groupbuy.web.server.handler.GetUserHandler;
import com.orange.groupbuy.web.server.util.SessionUtil;

@SuppressWarnings("serial")
public class SimpleDispatchServlet extends RemoteServiceServlet implements
		StandardDispatchService {

	private Dispatch dispatch;

	public SimpleDispatchServlet() {
		InstanceActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
		registry.addHandler(new GetCityNamesHandler());
		registry.addHandler(new GetGroupBuyCategoryListHandler());
		registry.addHandler(new GetMyGroupHandler());
		registry.addHandler(new GetUserHandler());
		dispatch = new SimpleDispatch(registry);
	}

	private HttpSession getSession() {
		// Get the current request and then return its session
		return this.getThreadLocalRequest().getSession();
	}

	@Override
	public Result execute(Action<?> action) throws DispatchException {

		try {
			SessionUtil.set(getSession());
			Result result = dispatch.execute(action);
			SessionUtil.set(null);
			return result;
		} catch (RuntimeException e) {
			log("Exception while executing " + action.getClass().getName()
					+ ": " + e.getMessage(), e);
			throw e;
		}
	}

}