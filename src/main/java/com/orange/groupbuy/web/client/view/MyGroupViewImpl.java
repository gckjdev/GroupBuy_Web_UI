package com.orange.groupbuy.web.client.view;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuyNavigationPanel;
import com.orange.groupbuy.web.client.component.GroupBuyWidget;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;

public class MyGroupViewImpl extends Composite implements MyGroupView {

	@UiField
	GroupBuyNavigationPanel myGroupNavigationPanel;

	@UiField
	FlowPanel searchResultPanel;

	public MyGroupViewImpl(EventBus eventBus, DispatchAsync dispatchAsync) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	private static MyGroupLayoutUiBinder uiBinder = GWT
			.create(MyGroupLayoutUiBinder.class);

	@UiTemplate("MyGroupLayout.ui.xml")
	interface MyGroupLayoutUiBinder extends UiBinder<Widget, MyGroupViewImpl> {
	}

	@Override
	public GroupBuyNavigationPanel getNavigationPanel() {
		return myGroupNavigationPanel;
	}

	@Override
	public void updateModel(List<SearchResult> searchResultList) {
		searchResultPanel.clear();
		HorizontalPanel resultRowPanel = null;
		for (int i = 0; i < searchResultList.size(); i++) {
			SearchResult result = searchResultList.get(i);
			if (i % 3 == 0) {
				resultRowPanel = new HorizontalPanel();
				// resultRowPanel.setStyleName("resultRowPanel-layout");
				searchResultPanel.add(resultRowPanel);
			}
			GroupBuyWidget resultComponent = new GroupBuyWidget();
			// resultComponent.setStyleName("GroupBuyWidget-layout");
			resultRowPanel.setSpacing(10);
			resultComponent.updateModel(result);
			resultRowPanel.add(resultComponent);
		}
	}

	@Override
	public ListBox getCitySelect() {
		// TODO Auto-generated method stub
		return null;
	}
}
