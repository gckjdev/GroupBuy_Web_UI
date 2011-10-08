package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.dispatch.GetGroupBuyCategory;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedHandler;
import com.orange.groupbuy.web.client.event.v1.KeywordSearchEvent;
import com.orange.groupbuy.web.client.event.v1.KeywordSearchHandler;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.ItemList;
import com.orange.groupbuy.web.client.model.OperationType;

public class SearchViewPresenter extends AbstractGroupBuyPresenter {

	private boolean init = true;

	public SearchViewPresenter(GroupBuyView display, EventBus eventBus) {
		super(display, eventBus);
	}

	private void refreshResult() {
		if (!validSearchText()) {
			return;
		}
		Criteria criteria = new Criteria();
		criteria.setOperationType(OperationType.KEYWORD_SEARCH);

		criteria.setKeyword(getDisplay().getSearchBox().getValue());

		String city = getDisplay().getCitySelect().getCity();
		criteria.setCity(city);
		// TODO: today
		// criteria.setOnlyToday(true);
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


						getDisplay().getNavigationPanel().getMyGroupBox()
								.removeFromParent();
						getDisplay().getNavigationPanel().getPriceBox()
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

		registerHandler(eventBus.addHandler(TabHeaderTabChangedEvent.getType(),
				new TabHeaderTabChangedHandler() {

					@Override
					public void onChanged(TabHeaderTabChangedEvent event) {
						if (init
								&& validSearchText()) {
							refreshResult();
							init = false;
						}
					}
				}));

		registerHandler(eventBus.addHandler(KeywordSearchEvent.getType(),
				new KeywordSearchHandler() {

					@Override
					public void onRefresh(KeywordSearchEvent keywordSearchEvent) {
						if (init && validSearchText()) {
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
				.getPreviousPage()
				.addClickHandler(new ClickHandler() {

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

	private boolean validSearchText() {
		return getDisplay().getSearchBox().getValue() != null && !getDisplay()
				.getSearchBox().getValue().isEmpty();
	}
}
