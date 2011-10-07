package com.orange.groupbuy.web.client.view;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuyNavigationPanel;
import com.orange.groupbuy.web.client.component.GroupBuyWidget;
import com.orange.groupbuy.web.client.component.PageListWidget;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.presenter.AbstractGroupBuyPresenter.GroupBuyView;

public abstract class AbstractGroupBuyView extends Composite implements
		GroupBuyView {

	private static final int RESULT_WIDGET_IN_ROW = 1;

	@UiField
	GroupBuyNavigationPanel myGroupNavigationPanel;

	@UiField
	FlowPanel searchResultPanel;

	@UiField
	PageListWidget pageNavigation;

	@UiField
	PageListWidget bottomPageNavigation;
	
	@UiField
	Label description;

	private final ListBox citySelect;

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

	@UiTemplate("AbstractGroupLayout.ui.xml")
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
		if (searchResultList.isEmpty()) {
			searchResultPanel.add(new Label("暂时没有此类团购"));
			return;
		}

		for (int i = 0; i < searchResultList.size(); i++) {
			SearchResult result = searchResultList.get(i);
			if (i % RESULT_WIDGET_IN_ROW == 0) {
				resultRowPanel = new HorizontalPanel();
				searchResultPanel.add(resultRowPanel);
			}
			GroupBuyWidget resultComponent = new GroupBuyWidget();
			resultRowPanel.setSpacing(10);
			resultComponent.updateModel(result);
			resultRowPanel.add(resultComponent);
		}
	}
	
	@Override
    public void updateModel(List<SearchResult> searchResultList, int rc) {
        searchResultPanel.clear();
        HorizontalPanel resultRowPanel = null;
        if (searchResultList.isEmpty()) {
            searchResultPanel.add(new Label("暂时没有此类团购"));
            getPageNavigation().setVisible(false);
            getBottomPageNavigation().setVisible(false);
            return;
        }
        
        getPageNavigation().setVisible(true);
        getBottomPageNavigation().setVisible(true);

        for (int i = 0; i < searchResultList.size(); i++) {
            SearchResult result = searchResultList.get(i);
            if (i % RESULT_WIDGET_IN_ROW == 0) {
                resultRowPanel = new HorizontalPanel();
                searchResultPanel.add(resultRowPanel);
            }
            GroupBuyWidget resultComponent = new GroupBuyWidget();
            resultRowPanel.setSpacing(10);
            resultComponent.updateModel(result);
            resultRowPanel.add(resultComponent);
        }
        
        pageNavigation.setTotalRows(rc);
        pageNavigation.setCurrentPage(pageNavigation.getCurrentPage());
        bottomPageNavigation.setTotalRows(rc);
        bottomPageNavigation.setCurrentPage(pageNavigation.getCurrentPage());

    }

//	@Override
//	public ListBox getCitySelect() {
//		return citySelect;
//	}

	@Override
	public PageListWidget getPageNavigation() {
		return pageNavigation;
	}

	@Override
	public PageListWidget getBottomPageNavigation() {
		return bottomPageNavigation;
	}

	@Override
    public Label getDescription() {
        return description;
    }
}
