package com.orange.groupbuy.web.client.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.orange.groupbuy.web.client.component.CityWidget;
import com.orange.groupbuy.web.client.component.GroupBuyNavigationPanel;
import com.orange.groupbuy.web.client.component.PageListWidget;
import com.orange.groupbuy.web.client.http.HttpClient;
import com.orange.groupbuy.web.client.http.HttpClient.Callback;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.presenter.AbstractGroupBuyPresenter.GroupBuyView;

public abstract class AbstractGroupBuyPresenter extends
		WidgetPresenter<GroupBuyView> {

	public final DispatchAsync dispatchAsync = new StandardDispatchAsync(
			new DefaultExceptionHandler());

	public AbstractGroupBuyPresenter(GroupBuyView display,
			EventBus eventBus) {
		super(display, eventBus);
	}

	public static interface GroupBuyView extends WidgetDisplay {
		GroupBuyNavigationPanel getNavigationPanel();

		void updateModel(List<SearchResult> searchResultList);

		void updateModel(List<SearchResult> resultList, int rc);

		CityWidget getCitySelect();

		PageListWidget getPageNavigation();
		
		PageListWidget getBottomPageNavigation();

        Label getDescription();
        
		TextBox getSearchBox();
		
	}

	protected void refreshResult(Criteria criteria) {

		// TODO: uncomment out
		// String city = getDisplay().getCitySelect().getCity();
		// criteria.setCity(city);

		HttpClient.searchGroupBuyHandler(criteria, new Callback() {

			@Override
			public void updateModel(List<SearchResult> resultList) {
				getDisplay().updateModel(resultList);
			}

            @Override
            public void updateModel(List<SearchResult> resultList, int rc) {
                getDisplay().updateModel(resultList, rc);
                
            }
		});
	}
	
	@Override
    protected void onBind() {
        //init city
          String autoCity = getAutoDetectedCity();
          String city = "";
          if (autoCity != null && !autoCity.isEmpty()) {
               city = autoCity.substring(0,autoCity.length()-1);
          }
          getDisplay().getCitySelect().setCity(city);
          
	}

	@Override
	protected void onUnbind() {

	}

	@Override
	protected void onRevealDisplay() {
	}
	
	protected native String getAutoDetectedCity()/*-{
		return $wnd.autoDetectedCity;
    }-*/;

}
