package com.orange.groupbuy.web.client.presenter;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.component.GroupBuyHeaderPanel;
import com.orange.groupbuy.web.client.dispatch.GetCityNames;
import com.orange.groupbuy.web.client.event.CityChangedEvent;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.model.CityNames;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.presenter.MainPresenter.MainView;

public class MainPresenter extends WidgetPresenter<MainView> {

	private final DispatchAsync dispatch = new StandardDispatchAsync(
			new DefaultExceptionHandler());

	public static interface MainView extends WidgetDisplay {
		TabLayoutPanel getTabHeader();

		GroupBuyHeaderPanel getHeaderPanel();

		ListBox getCitySelect();
	}



	public MainPresenter(MainView display, EventBus eventBus) {
		super(display, eventBus);
	}

	public MainPresenter(MainView display, EventBus eventBus,
			DispatchAsync dispatchAsync) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
		registerHandler(getDisplay().getCitySelect().addAttachHandler(
				new Handler() {

					@Override
					public void onAttachOrDetach(AttachEvent event) {
						dispatch.execute(new GetCityNames(),
								new SimpleCallback<CityNames>() {

									@Override
									public void onSuccess(CityNames result) {
										ArrayList<Item> cityList = result
												.getCityList();
										getDisplay().getCitySelect().clear();
										for (Item city : cityList) {
											getDisplay()
													.getCitySelect()
													.addItem(
															city.getDisplayName(),
															city.getValue());
										}
										// init the city
										initDefaultCitySelect();
									}
								});
					}
				}));

		registerHandler(getDisplay().getCitySelect().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						eventBus.fireEvent(new CityChangedEvent());
					}
				}));

		registerHandler(getDisplay().getTabHeader().addSelectionHandler(
				new SelectionHandler<Integer>() {

					@Override
					public void onSelection(SelectionEvent<Integer> event) {
						Integer selectItem = event.getSelectedItem();
						if (selectItem == 0) {
							// mygroup
						} else if (selectItem == 1) {
							//
						} else if (selectItem == 2) {

						}

						eventBus.fireEvent(new TabHeaderTabChangedEvent());
					}
				}));
	}

	private native String getAutoDetectedCity()/*-{
		return $wnd.autoDetectedCity;
	}-*/;

	private void initDefaultCitySelect() {
		String autoCity = getAutoDetectedCity();
		if (autoCity != null && !autoCity.isEmpty()) {
			int selectedIndex = getSelectedIndex(autoCity);
			if (selectedIndex != -1) {
				getDisplay().getCitySelect().setSelectedIndex(selectedIndex);
			}
		}
	}

	private int getSelectedIndex(String autoCity) {
		int selectedIndex = -1;
		int count = getDisplay().getCitySelect().getItemCount();
		for (int index = 0; index < count; index++) {
			String value = getDisplay().getCitySelect().getValue(index);
			if (!value.isEmpty()) {
				// TODO: some bug here.
				if (autoCity.contains(value)) {
					selectedIndex = index;
					break;
				}
			}
		}
		return selectedIndex;
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
