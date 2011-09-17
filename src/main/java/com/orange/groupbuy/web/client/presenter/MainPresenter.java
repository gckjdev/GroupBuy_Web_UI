package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.presenter.MainPresenter.MainView;


public class MainPresenter extends WidgetPresenter<MainView> {

	public static interface MainView extends WidgetDisplay {
		public TabLayoutPanel getTabHeader();
	}

	public MainPresenter(MainView display, EventBus eventBus) {
		super(display, eventBus);
		// TODO Auto-generated constructor stub
	}

	public MainPresenter(MainView display, EventBus eventBus,
			DispatchAsync dispatchAsync) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
		registerHandler(getDisplay().getTabHeader().addSelectionHandler(
				new SelectionHandler<Integer>() {

					@Override
					public void onSelection(SelectionEvent<Integer> event) {
						Integer selectItem = event.getSelectedItem();
						if (selectItem == 0) {
							// mygroup
						} else if (selectItem == 1) {
							//
						} else if (selectItem == 2) {

						}

						eventBus.fireEvent(new TabHeaderTabChangedEvent());
					}
				}));
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
