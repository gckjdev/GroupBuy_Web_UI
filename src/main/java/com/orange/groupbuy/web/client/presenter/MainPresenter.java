package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.orange.groupbuy.web.client.presenter.MainPresenter.MainView;


public class MainPresenter extends WidgetPresenter<MainView> {

	public static interface MainView extends WidgetDisplay {

	}

	public MainPresenter(MainView display, EventBus eventBus) {
		super(display, eventBus);
		// TODO Auto-generated constructor stub
	}

	public MainPresenter(MainView display, EventBus eventBus,
			DispatchAsync dispatchAsync) {
		super(display, eventBus);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onBind() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRevealDisplay() {
		// TODO Auto-generated method stub
	}

}
