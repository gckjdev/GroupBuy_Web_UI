package com.orange.groupbuy.web.client.presenter;

import java.util.ArrayList;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.dispatch.GetGroupBuyCategory;
import com.orange.groupbuy.web.client.event.CityChangedEvent;
import com.orange.groupbuy.web.client.event.CityChangedHandler;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedHandler;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.ItemList;
import com.orange.groupbuy.web.client.model.OperationType;
import com.orange.groupbuy.web.client.model.PriceItem;

public class TopViewPresenter extends AbstractGroupBuyPresenter {

	private boolean init = true;

	public TopViewPresenter(GroupBuyView display, EventBus eventBus) {
		super(display, eventBus);
	}

	private void refreshResult() {
		Criteria criteria = new Criteria();
		criteria.setOperationType(OperationType.TOP_SHOW);
		// category list
		ArrayList<String> categoryList = getDisplay().getNavigationPanel()
				.getSelectedCategoryList();
		criteria.setCategoryList(categoryList);

		PriceItem item = getDisplay().getNavigationPanel().getSelectedPrice();
		// price
		criteria.setStartPrice(item.getMin());
		criteria.setEndPrice(item.getMax());
		// city
		String city = getDisplay().getCitySelect().getValue(
				getDisplay().getCitySelect().getSelectedIndex());
		criteria.setCity(city);
		// current page
		criteria.setPageSize(getDisplay().getPageNavigation().getPageSize());
		criteria.setStartRow(getDisplay().getPageNavigation().getStartRow());
		refreshResult(criteria);
	}

	@Override
	protected void onBind() {
		registerHandler(getDisplay().getNavigationPanel().addAttachHandler(
				new Handler() {
					@Override
					public void onAttachOrDetach(AttachEvent event) {
						// register call back;
						getDisplay()
								.getNavigationPanel()
								.getCategroyBox()
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

						getDisplay().getNavigationPanel().getMyGroupBox()
								.removeFromParent();

						dispatchAsync.execute(new GetGroupBuyCategory(),
								new SimpleCallback<ItemList>() {

									@Override
									public void onSuccess(ItemList result) {
										final CellTable<Item> categorySelection = getDisplay()
												.getNavigationPanel()
												.getCategroyBox()
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
						if (init) {
							refreshResult();
							init = false;
						}
					}
				}));

		// previous
		registerHandler(getDisplay().getPageNavigation().getPreviousPage()
				.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						getDisplay().getPageNavigation().previousPage();
						refreshResult();
					}
				}));
		// next
		registerHandler(getDisplay().getPageNavigation().getNextPage()
				.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						getDisplay().getPageNavigation().nextPage();
						refreshResult();
					}
				}));
	}
}
