package com.orange.groupbuy.web.client.presenter;

import java.util.ArrayList;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
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

public class TodayViewPresenter extends AbstractGroupBuyPresenter {

	private boolean init = true;

	public TodayViewPresenter(GroupBuyView display, EventBus eventBus) {
		super(display, eventBus);
	}

	private void refreshResult() {
		Criteria criteria = new Criteria();
		criteria.setOperationType(OperationType.CATEGORY_SHOW);

		// today
		criteria.setOnlyToday(true);
		
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
		
		String city = getDisplay().getCitySelect().getCity();
        criteria.setCity(city);
        
		// current page
		criteria.setPageSize(getDisplay().getPageNavigation().getPageSize());
		criteria.setStartRow(getDisplay().getPageNavigation().getStartRow());
		refreshResult(criteria);
		
		//refresh description bar
        categoryList = this.getDisplay().getNavigationPanel().getSelectedCategoryNameList();
        StringBuilder description = new StringBuilder();

        description.append("今日");
        for (int i=0;i<categoryList.size();i++) {
            description.append(categoryList.get(i));
            if (i != categoryList.size()-1) {
                description.append(" ");
            }
        }
        description.append("团购");
        getDisplay().getDescription().setText(description.toString());
	}

	@Override
	protected void onBind() {
	    super.onBind();
	    
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
						
						dispatchAsync.execute(new GetGroupBuyCategory(getDisplay().getCitySelect().getCity()),
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
	                        
	                        dispatchAsync.execute(new GetGroupBuyCategory(getDisplay().getCitySelect().getCity()),
	                                new SimpleCallback<ItemList>() {

	                                    @Override
	                                    public void onSuccess(ItemList result) {
	                                        final CellTable<Item> categorySelection = getDisplay()
	                                                .getNavigationPanel()
	                                                .getCategroyBox()
	                                                .getContentCellTable();
	                                        categorySelection.setRowData(0,
	                                                result.getItems());
	                                        
	                                        // set the last select category
	                                        Item item = getDisplay().getNavigationPanel().getLastSelectItem();
	                                        if (item.getValue() != null && item.getValue().length() > 0) {
	                                            
	                                            for (int i=0;i<result.getItems().size();i++) {
	                                                if (result.getItems().get(i).getValue().equals(item.getValue())) {
	                                                    item = result.getItems().get(i);
	                                                    break;
	                                                }
	                                            }
	                                            @SuppressWarnings({ "unchecked", "rawtypes" })
	                                            SingleSelectionModel<Item> selected  = (SingleSelectionModel) categorySelection.getSelectionModel();
	                                            selected.setSelected(item, true);
	                                        }
	                                    }
	                                });
	                    }
	                }));


		registerHandler(eventBus.addHandler(TabHeaderTabChangedEvent.getType(),
				new TabHeaderTabChangedHandler() {

					@Override
					public void onChanged(TabHeaderTabChangedEvent event) {
						if(event.getIndex() != 1) return;
						if (init) {
							refreshResult();
							init = false;
						}else{
							getDisplay().resize();
						}
//						Window.alert("Today");
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
	
}
