package com.orange.groupbuy.web.client.presenter.v1;

import java.util.ArrayList;
import java.util.Collection;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.component.PageListWidget;
import com.orange.groupbuy.web.client.dispatch.GetCityNames;
import com.orange.groupbuy.web.client.event.v1.KeywordSearchEvent;
import com.orange.groupbuy.web.client.event.v1.KeywordSearchHandler;
import com.orange.groupbuy.web.client.event.v1.RefreshSearchResultEvent;
import com.orange.groupbuy.web.client.event.v1.RefreshSearchResultHandler;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.CityNames;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.OrderType;
import com.orange.groupbuy.web.client.view.v1.GroupBuySearchView;

public class GroupBuySearchViewPresenter extends
		WidgetPresenter<GroupBuySearchView> {

	private DispatchAsync dispatch;

	public GroupBuySearchViewPresenter(GroupBuySearchView display,
			EventBus eventBus, DispatchAsync dispatch) {
		super(display, eventBus);
		this.dispatch = dispatch;
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

		Collection<TabLayoutPanel> orderTypePanelList = getDisplay()
				.getOrderTypeTabPanelList().values();
		for (TabLayoutPanel orderTab : orderTypePanelList) {
			registerHandler(orderTab
					.addSelectionHandler(new SelectionHandler<Integer>() {

						@Override
						public void onSelection(SelectionEvent<Integer> event) {
							eventBus.fireEvent(createRefreshEvent());
						}
					}));
		}

		// previous page
		Collection<PageListWidget> pageListWidgetList = getDisplay()
				.getPageListWidgetList().values();
		for (final PageListWidget page : pageListWidgetList) {
			// previous
			registerHandler(page.getPreviousPage().addClickHandler(
					new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							page.previousPage();
							eventBus.fireEvent(createRefreshEvent());
						}
					}));
			// next
			registerHandler(page.getNextPage().addClickHandler(
					new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							page.nextPage();
							eventBus.fireEvent(createRefreshEvent());
						}
					}));
		}

		// init load
		getDisplay().getCategoryTabPanel().addAttachHandler(new Handler() {

			@Override
			public void onAttachOrDetach(AttachEvent event) {
				RefreshSearchResultEvent refreshSearchResultEvent = new RefreshSearchResultEvent();
				refreshSearchResultEvent.setCategory(Category.getDisplayOrder()[0]);
				refreshSearchResultEvent.setOrderType(OrderType
						.getDisplayOrder()[0]);

				// No city select
				refreshSearchResultEvent.setCity("");
				refreshSearchResultEvent.setCurrentPage(1);
				refreshSearchResultEvent.setOnlyToday(false);
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

		// keyword search
		registerHandler(getDisplay().getSearchButton().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						String value = getDisplay().getSearchTextBox()
								.getText();
						if (value == null || value.trim().isEmpty()) {
							// TODO: validate check
							return;
						}
						eventBus.fireEvent(createKeyworkSearchEvent());
					}
				}));

		registerHandler(eventBus.addHandler(KeywordSearchEvent.getType(),
				new KeywordSearchHandler() {

					@Override
					public void onRefresh(KeywordSearchEvent event) {
						// select the first one.
						getDisplay().getCategoryTabPanel().selectTab(0);
						// hide and show
						getDisplay().getKeywordResultPanel().setVisible(true);

						getDisplay().getCategoryAllPanel().setVisible(false);
					}

				}));

		registerHandler(eventBus.addHandler(RefreshSearchResultEvent.getType(),
				new RefreshSearchResultHandler() {

					@Override
					public void onRefresh(RefreshSearchResultEvent event) {
						// hide and show
						getDisplay().getKeywordResultPanel().setVisible(false);
						getDisplay().getCategoryAllPanel().setVisible(true);
					}

				}));
	}

	private RefreshSearchResultEvent createRefreshEvent() {
		RefreshSearchResultEvent refreshSearchResultEvent = new RefreshSearchResultEvent();
		int cityIndex = getDisplay().getCitySelects().getSelectedIndex();
		String city = getDisplay().getCitySelects().getValue(cityIndex);
		refreshSearchResultEvent.setCity(city);

		int categoryIndex = getDisplay().getCategoryTabPanel()
				.getSelectedIndex();
		Category category = Category.getDisplayOrder()[categoryIndex];
		refreshSearchResultEvent.setCategory(category);

		int orderTypeIndex = getDisplay().getOrderTypeTabPanelList()
				.get(category.name()).getSelectedIndex();
		OrderType orderType = OrderType.getDisplayOrder()[orderTypeIndex];
		refreshSearchResultEvent.setOrderType(orderType);

		boolean onlyToday = getDisplay().getOnlyTodayCheckBox().getValue();
		refreshSearchResultEvent.setOnlyToday(onlyToday);

		// page
		PageListWidget pageList = getDisplay().getPageListWidgetList().get(
				category.getIdentify(orderType));
		refreshSearchResultEvent.setCurrentPage(pageList.getCurrentPage());
		return refreshSearchResultEvent;
	}

	private KeywordSearchEvent createKeyworkSearchEvent() {
		KeywordSearchEvent event = new KeywordSearchEvent();
		// city
		int cityIndex = getDisplay().getCitySelects().getSelectedIndex();
		String city = getDisplay().getCitySelects().getValue(cityIndex);
		event.setCity(city);
		// only today
		boolean onlyToday = getDisplay().getOnlyTodayCheckBox().getValue();
		event.setOnlyToday(onlyToday);
		// current page
		event.setCurrentPage(getDisplay().getKeywordPageList().getCurrentPage());
		// keyword
		String keyword = getDisplay().getSearchTextBox().getText();
		event.setKeyword(keyword);
		return event;
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
