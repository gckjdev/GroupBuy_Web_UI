package com.orange.groupbuy.web.client.presenter;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.orange.groupbuy.web.client.event.RefreshSearchResultEvent;
import com.orange.groupbuy.web.client.event.RefreshSearchResultHandler;
import com.orange.groupbuy.web.client.http.HttpClient;
import com.orange.groupbuy.web.client.http.HttpClient.Callback;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.OrderType;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.view.GroupBuyResultView;

public class GroupBuyResultViewPresenter extends
		WidgetPresenter<GroupBuyResultView> {

	private Category category;
	private OrderType orderType;

	public GroupBuyResultViewPresenter(GroupBuyResultView display,
			EventBus eventBus, Category category,
			OrderType orderType) {
		super(display, eventBus);
		this.category = category;
		this.orderType = orderType;
	}

	@Override
	protected void onBind() {
		registerHandler(eventBus.addHandler(RefreshSearchResultEvent.getType(),
				new RefreshSearchResultHandler() {

					@Override
					public void onRefresh(RefreshSearchResultEvent event) {
						if (isTargetPanel(event)) {

							// build criteria
							Criteria criteria = new Criteria();
							criteria.setCategory(event.getCategory());
							criteria.setOrderType(event.getOrderType());
							criteria.setCity(event.getCity());
							criteria.setOnlyToday(event.isOnlyToday());
							criteria.setPageSize(event.getPageSize());
							criteria.setStartRow(event.getStartRow());

							HttpClient.searchGroupBuyHandler(criteria,
									new Callback() {

										@Override
										public void updateModel(
												List<SearchResult> resultList) {
											getDisplay()
													.updateModel(resultList);
										}
									});

							// TODO:
							// SearchGroupBuy action = new SearchGroupBuy();
							// action.setCriteria(criteria);
							// dispatch.execute(action,
							// new SimpleCallback<SearchResultList>() {
							//
							// @Override
							// public void onSuccess(
							// SearchResultList result) {
							// getDisplay().updateModel(
							// result.getResults());
							// }
							// });
						}
					}

					private boolean isTargetPanel(RefreshSearchResultEvent event) {
						return category == event.getCategory()
								&& orderType == event.getOrderType();
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
