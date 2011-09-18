package com.orange.groupbuy.web.client.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.component.GroupBuyNavigationPanel;
import com.orange.groupbuy.web.client.dispatch.GetMyGroup;
import com.orange.groupbuy.web.client.event.CityChangedEvent;
import com.orange.groupbuy.web.client.event.CityChangedHandler;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedHandler;
import com.orange.groupbuy.web.client.http.HttpClient;
import com.orange.groupbuy.web.client.http.HttpClient.Callback;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.ItemList;
import com.orange.groupbuy.web.client.model.PriceItem;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;

public class MyGroupPresenter extends WidgetPresenter<MyGroupView> {

	private final DispatchAsync dispatchAsync = new StandardDispatchAsync(
			new DefaultExceptionHandler());

	public static interface MyGroupView extends WidgetDisplay {
		GroupBuyNavigationPanel getNavigationPanel();

		void updateModel(List<SearchResult> searchResultList);

		ListBox getCitySelect();
	}

	public MyGroupPresenter(MyGroupView display, EventBus eventBus) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
		registerHandler(getDisplay().getNavigationPanel()
				.addAttachHandler(new Handler() {

					@Override
					public void onAttachOrDetach(AttachEvent event) {
						// register call back;
						getDisplay()
								.getNavigationPanel()
								.getMyGroupBox()
								.getContentCellTable()
								.getSelectionModel()
								.addSelectionChangeHandler(
										new SelectionChangeEvent.Handler() {
											@Override
											public void onSelectionChange(
													SelectionChangeEvent event) {
												refreshResult();
											}
										});


						getDisplay()
								.getNavigationPanel()
								.getPriceBox()
								.getContentCellTable()
								.getSelectionModel()
								.addSelectionChangeHandler(
										new SelectionChangeEvent.Handler() {
											@Override
											public void onSelectionChange(
													SelectionChangeEvent event) {
												refreshResult();
											}
										});

						getDisplay().getNavigationPanel()
								.getCategroyBox().removeFromParent();
						dispatchAsync.execute(new GetMyGroup(),
								new SimpleCallback<ItemList>() {

									@Override
									public void onSuccess(ItemList result) {
										final CellTable<Item> categorySelection = getDisplay()
												.getNavigationPanel()
												.getMyGroupBox()
												.getContentCellTable();
										categorySelection.setRowData(0,
												result.getItems());
									}
								});
					}
				}));

		registerHandler(eventBus.addHandler(CityChangedEvent.getType(),
				new CityChangedHandler() {

					@Override
					public void onChanged(CityChangedEvent event) {
						refreshResult();
					}
				}));

		registerHandler(eventBus.addHandler(TabHeaderTabChangedEvent.getType(),
				new TabHeaderTabChangedHandler() {

					@Override
					public void onChanged(TabHeaderTabChangedEvent event) {
						// TODO refresh result panel
					}
				}));

	}

	private void refreshResult() {
		// category list
		ArrayList<String> categoryList = getDisplay().getNavigationPanel()
				.getSelectedCategoryList();
		Criteria criteria = new Criteria();
		criteria.setCategoryList(categoryList);

		PriceItem item = getDisplay().getNavigationPanel()
				.getSelectedPrice();
		// price
		criteria.setStartPrice(item.getMin());
		criteria.setEndPrice(item.getMax());

		// TODO: city
		criteria.setCity("全国");

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
