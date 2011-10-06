package com.orange.groupbuy.web.client;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.orange.groupbuy.web.client.presenter.MainPresenter;
import com.orange.groupbuy.web.client.presenter.v1.GroupBuySearchViewPresenter;
import com.orange.groupbuy.web.client.view.MainViewImpl;
import com.orange.groupbuy.web.client.view.v1.GruopBuySearchViewImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GroupBuy_Web_UI implements EntryPoint {

	private final DispatchAsync dispatchAsync = new StandardDispatchAsync(
			new DefaultExceptionHandler());

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		// initView();
		initView2();
	}

	private void initView2() {
		// Build the default event bus
		EventBus eventBus = new DefaultEventBus();

		// Build the display and presenter
		MainViewImpl display = new MainViewImpl(eventBus, dispatchAsync);
		MainPresenter presenter = new MainPresenter(display,
				eventBus, dispatchAsync);

		// Bind the presenter to the display.
		presenter.bind();
		RootPanel.get().add(presenter.getDisplay().asWidget());
		// RootLayoutPanel.get().add(presenter.getDisplay().asWidget());
		// RootLayoutPanel.get().setHeight("5000px");
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void initView() {
		// Build the default event bus
		EventBus eventBus = new DefaultEventBus();

		// Build the display and presenter
		GruopBuySearchViewImpl display = new GruopBuySearchViewImpl(eventBus,
				dispatchAsync);
		GroupBuySearchViewPresenter presenter = new GroupBuySearchViewPresenter(
				display, eventBus, dispatchAsync);

		// Bind the presenter to the display.
		presenter.bind();

		RootPanel rootPanel = RootPanel.get("contentContainer");
		rootPanel.add(presenter.getDisplay().asWidget());
	}
}
