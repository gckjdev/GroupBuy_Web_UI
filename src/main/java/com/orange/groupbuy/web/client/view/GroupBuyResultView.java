package com.orange.groupbuy.web.client.view;

import java.util.List;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.orange.groupbuy.web.client.model.SearchResult;

public interface GroupBuyResultView extends WidgetDisplay {

	public void updateModel(List<SearchResult> searchResultList);

}