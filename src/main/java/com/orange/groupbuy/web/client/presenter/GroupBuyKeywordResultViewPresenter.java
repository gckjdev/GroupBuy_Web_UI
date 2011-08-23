package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.orange.groupbuy.web.client.event.RefreshSearchResultEvent;
import com.orange.groupbuy.web.client.event.RefreshSearchResultHandler;
import com.orange.groupbuy.web.client.view.GroupBuyResultView;

public class GroupBuyKeywordResultViewPresenter extends
		WidgetPresenter<GroupBuyResultView> {

	public GroupBuyKeywordResultViewPresenter(GroupBuyResultView display,
			EventBus eventBus) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
		registerHandler(eventBus.addHandler(RefreshSearchResultEvent.getType(),
				new RefreshSearchResultHandler() {

					@Override
					public void onRefresh(RefreshSearchResultEvent event) {

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
