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
	private String city;
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
//		String city = getDisplay().getCitySelect().getValue(
//				getDisplay().getCitySelect().getSelectedIndex());
		
		criteria.setCity(city);
		// current page
		criteria.setPageSize(getDisplay().getPageNavigation().getPageSize());
		criteria.setStartRow(getDisplay().getPageNavigation().getStartRow());
		refreshResult(criteria);

		//refresh description
		categoryList = this.getDisplay().getNavigationPanel().getSelectedCategoryNameList();
		StringBuilder description = new StringBuilder();

		for (int i=0;i<categoryList.size();i++) {
		    description.append(categoryList.get(i) +" ");
		}
		
		description.append(item.getMin() + "-" + item.getMax());
		getDisplay().getDescription().setText(description.toString());
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
						
                        GetGroupBuyCategory category = new GetGroupBuyCategory();
                        category.setCity(city);
						dispatchAsync.execute(category,
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
					    TopViewPresenter.this.city = event.getCityName();
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
						previousPage();
					}
				}));
		// next
		registerHandler(getDisplay().getPageNavigation().getNextPage()
				.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						nextPage();
					}
				}));
		// previous
		registerHandler(getDisplay().getBottomPageNavigation()
				.getPreviousPage().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						previousPage();
					}
				}));
		// next
		registerHandler(getDisplay().getBottomPageNavigation().getNextPage()
				.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						nextPage();
					}
				}));
	}

	private void nextPage() {
		getDisplay().getBottomPageNavigation().nextPage();
		getDisplay().getPageNavigation().nextPage();
		refreshResult();
	}

	private void previousPage() {
		getDisplay().getBottomPageNavigation().previousPage();
		getDisplay().getPageNavigation().previousPage();
		refreshResult();
	}
}
