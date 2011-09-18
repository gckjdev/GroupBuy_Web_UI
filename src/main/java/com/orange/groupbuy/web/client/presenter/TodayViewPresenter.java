package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.dispatch.GetGroupBuyCategory;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedHandler;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.ItemList;
import com.orange.groupbuy.web.client.model.OperationType;
import com.orange.groupbuy.web.client.model.PriceItem;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;

public class TodayViewPresenter extends AbstractGroupBuyPresenter {

	public TodayViewPresenter(MyGroupView display, EventBus eventBus) {
		super(display, eventBus);
	}

	private void refreshResult() {
		Criteria criteria = new Criteria();
		criteria.setOperationType(OperationType.CATEGORY_SHOW);

		criteria.setOnlyToday(true);

		PriceItem item = getDisplay().getNavigationPanel().getSelectedPrice();
		// price
		criteria.setStartPrice(item.getMin());
		criteria.setEndPrice(item.getMax());

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
						// TODO refresh result panel
					}
				}));

	}
}
