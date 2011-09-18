package com.orange.groupbuy.web.client.view;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuyNavigationPanel;
import com.orange.groupbuy.web.client.component.GroupBuyWidget;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;

public abstract class AbstractGroupBuyView extends Composite implements
		MyGroupView {

	@UiField
	GroupBuyNavigationPanel myGroupNavigationPanel;

	@UiField
	ScrollPanel searchResultPanel;

	private ListBox citySelect;

	public AbstractGroupBuyView(EventBus eventBus, ListBox citySelect) {
		initWidget(uiBinder.createAndBindUi(this));
		this.citySelect = citySelect;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	private static MyGroupLayoutUiBinder uiBinder = GWT
			.create(MyGroupLayoutUiBinder.class);

	@UiTemplate("MyGroupLayout.ui.xml")
	interface MyGroupLayoutUiBinder extends UiBinder<Widget, AbstractGroupBuyView> {
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

	public ListBox getCitySelect() {
		return citySelect;
	}
}
