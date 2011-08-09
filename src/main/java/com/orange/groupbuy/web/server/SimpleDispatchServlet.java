package com.orange.groupbuy.web.server;

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
import com.orange.groupbuy.web.server.handler.SearchGroupBuyHandler;

@SuppressWarnings("serial")
public class SimpleDispatchServlet extends RemoteServiceServlet implements
		StandardDispatchService {

	private Dispatch dispatch;

	public SimpleDispatchServlet() {
		InstanceActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
		registry.addHandler(new SearchGroupBuyHandler());
		registry.addHandler(new GetCityNamesHandler());
		dispatch = new SimpleDispatch(registry);
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