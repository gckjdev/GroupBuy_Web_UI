package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.dispatch.SearchGroupBuy;
import com.orange.groupbuy.web.client.event.RefreshSearchResultEvent;
import com.orange.groupbuy.web.client.event.RefreshSearchResultHandler;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.OrderType;
import com.orange.groupbuy.web.client.model.SearchResultList;
import com.orange.groupbuy.web.client.view.GroupBuyResultView;

public class GroupBuyResultViewPresenter extends
		WidgetPresenter<GroupBuyResultView> {


	private DispatchAsync dispatch;
	private Category category;
	private OrderType orderType;

	public GroupBuyResultViewPresenter(GroupBuyResultView display,
			EventBus eventBus, DispatchAsync dispatch, Category category,
			OrderType orderType) {
		super(display, eventBus);
		this.dispatch = dispatch;
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
							SearchGroupBuy action = new SearchGroupBuy();
							// build criteria
							Criteria criteria = new Criteria();
							criteria.setCategory(Category.valueOf(event
									.getCategory()));
							criteria.setOrderType(OrderType.valueOf(event
									.getOrderType()));
							criteria.setCity(event.getCity());
							// TODO:
							// criteria.setOnlyToday(onlyToday);

							action.setCriteria(criteria);
							dispatch.execute(action,
									new SimpleCallback<SearchResultList>() {

										@Override
										public void onSuccess(
												SearchResultList result) {
											getDisplay().updateModel(
													result.getResults());
										}
									});
						}
					}

					private boolean isTargetPanel(RefreshSearchResultEvent event) {
						return category.name().equals(event.getCategory())
								&& orderType.name()
										.equals(event.getOrderType());
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
