package com.orange.groupbuy.web.client.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.orange.groupbuy.web.client.http.HttpClient;
import com.orange.groupbuy.web.client.http.HttpClient.Callback;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;

public abstract class AbstractGroupBuyPresenter extends
		WidgetPresenter<MyGroupView> {

	public final DispatchAsync dispatchAsync = new StandardDispatchAsync(
			new DefaultExceptionHandler());

	public AbstractGroupBuyPresenter(MyGroupView display, EventBus eventBus) {
		super(display, eventBus);
	}


	protected void refreshResult(Criteria criteria) {

		String city = getDisplay().getCitySelect().getValue(
				getDisplay().getCitySelect().getSelectedIndex());
		criteria.setCity(city);

		HttpClient.searchGroupBuyHandler(criteria, new Callback() {

			@Override
			public void updateModel(List<SearchResult> resultList) {
				getDisplay().updateModel(resultList);
			}
		});
	}

	@Override
	protected void onUnbind() {

	}

	@Override
	protected void onRevealDisplay() {
	}
}
