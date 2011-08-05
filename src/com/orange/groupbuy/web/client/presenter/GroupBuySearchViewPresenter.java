package com.orange.groupbuy.web.client.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.dispatch.GetCityNames;
import com.orange.groupbuy.web.client.event.RefreshSearchResultEvent;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.CityNames;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.OrderType;
import com.orange.groupbuy.web.client.view.GroupBuySearchView;

public class GroupBuySearchViewPresenter extends
		WidgetPresenter<GroupBuySearchView> {

	private DispatchAsync dispatch;

	public GroupBuySearchViewPresenter(GroupBuySearchView display,
			EventBus eventBus, DispatchAsync dispatch) {
		super(display, eventBus);
		this.dispatch = dispatch;
	}

	private RefreshSearchResultEvent createRefreshEvent() {
		RefreshSearchResultEvent refreshSearchResultEvent = new RefreshSearchResultEvent();
		int cityIndex = getDisplay().getCitySelects().getSelectedIndex();
		String city = getDisplay().getCitySelects().getValue(cityIndex);
		refreshSearchResultEvent.setCity(city);

		int categoryIndex = getDisplay().getCategoryTabPanel()
				.getSelectedIndex();
		String category = Category.getDisplayOrder()[categoryIndex].name();
		refreshSearchResultEvent.setCategory(category);

		int orderTypeIndex = getDisplay().getOrderTypeTabPanelList()
				.get(categoryIndex).getSelectedIndex();
		String orderType = OrderType.getDisplayOrder()[orderTypeIndex].name();
		refreshSearchResultEvent.setOrderType(orderType);

		boolean onlyToday = getDisplay().getOnlyTodayCheckBox().getValue();
		refreshSearchResultEvent.setOnlyToday(onlyToday);
		return refreshSearchResultEvent;
	}

	@Override
	protected void onBind() {

		registerHandler(getDisplay().getCitySelects().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						eventBus.fireEvent(createRefreshEvent());
					}
				}));

		registerHandler(getDisplay().getOnlyTodayCheckBox()
				.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						eventBus.fireEvent(createRefreshEvent());
					}
				}));

		registerHandler(getDisplay().getCategoryTabPanel().addSelectionHandler(
				new SelectionHandler<Integer>() {

					@Override
					public void onSelection(SelectionEvent<Integer> event) {
						eventBus.fireEvent(createRefreshEvent());
					}

				}));

		List<TabLayoutPanel> orderTypePanelList = getDisplay()
				.getOrderTypeTabPanelList();
		for (TabLayoutPanel orderTab : orderTypePanelList) {
			registerHandler(orderTab
					.addSelectionHandler(new SelectionHandler<Integer>() {

						@Override
						public void onSelection(SelectionEvent<Integer> event) {
							eventBus.fireEvent(createRefreshEvent());
						}
					}));
		}

		// init load
		getDisplay().getCategoryTabPanel().addAttachHandler(new Handler() {

			@Override
			public void onAttachOrDetach(AttachEvent event) {
				RefreshSearchResultEvent refreshSearchResultEvent = new RefreshSearchResultEvent();
				refreshSearchResultEvent.setCategory(Category.getDisplayOrder()[0]
						.name());
				refreshSearchResultEvent.setOrderType(OrderType
						.getDisplayOrder()[0].name());

				// No city select
				refreshSearchResultEvent.setCity("");
				eventBus.fireEvent(refreshSearchResultEvent);
			}
		});

		registerHandler(getDisplay().getCitySelects().addAttachHandler(
				new Handler() {

					@Override
					public void onAttachOrDetach(AttachEvent event) {
						dispatch.execute(new GetCityNames(),
								new SimpleCallback<CityNames>() {

									@Override
									public void onSuccess(CityNames result) {
										ArrayList<Item> cityList = result
												.getCityList();
										getDisplay().getCitySelects().clear();
										for (Item city : cityList) {
											getDisplay()
													.getCitySelects()
													.addItem(
															city.getDisplayName(),
															city.getValue());
										}
									}
								});
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

	public DispatchAsync getDispatch() {
		return dispatch;
	}

}
