package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;

public class MyGroupPresenter extends WidgetPresenter<MyGroupView> {


	public static interface MyGroupView extends WidgetDisplay {

	}

	public MyGroupPresenter(MyGroupView display, EventBus eventBus) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
	}

	@Override
	protected void onUnbind() {

	}

	@Override
	protected void onRevealDisplay() {
	}
}
