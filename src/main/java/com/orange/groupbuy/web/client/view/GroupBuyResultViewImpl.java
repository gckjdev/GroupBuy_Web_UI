package com.orange.groupbuy.web.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuyWidget;
import com.orange.groupbuy.web.client.model.SearchResult;

public class GroupBuyResultViewImpl extends Composite implements
		GroupBuyResultView {

	private VerticalPanel compositePanel;

	public GroupBuyResultViewImpl() {
		compositePanel = new VerticalPanel();
		initWidget(compositePanel);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void updateModel(List<SearchResult> searchResultList) {
		compositePanel.clear();
		HorizontalPanel resultRowPanel = null;
		for (int i = 0; i < searchResultList.size(); i++) {
			SearchResult result = searchResultList.get(i);
			if (i % 3 == 0) {
				resultRowPanel = new HorizontalPanel();
				resultRowPanel.setStyleName("resultRowPanel-layout");
				compositePanel.add(resultRowPanel);
			}
			GroupBuyWidget resultComponent = new GroupBuyWidget();
			resultComponent.setStyleName("GroupBuyWidget-layout");
			resultRowPanel.setSpacing(10);
			resultComponent.updateModel(result);
			resultRowPanel.add(resultComponent);
		}
	}
}
