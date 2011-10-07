package com.orange.groupbuy.web.client.view.v1;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuySearchHeader;
import com.orange.groupbuy.web.client.component.PageListWidget;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.OrderType;
import com.orange.groupbuy.web.client.presenter.v1.GroupBuyKeywordResultViewPresenter;
import com.orange.groupbuy.web.client.presenter.v1.GroupBuyResultViewPresenter;

public class GruopBuySearchViewImpl extends Composite implements
		GroupBuySearchView {

	private final GroupBuySearchHeader header;
	private final CheckBox onlyTodayCheckBox;
	private final TabLayoutPanel categoryTabPanel;
	private final Map<String, TabLayoutPanel> orderTypeTabPanelList;
	private final Map<String, PageListWidget> pageListWidgetList;
	private GroupBuyResultView keywordSearchPanel;
	private PageListWidget keywordPageList;
	private HorizontalPanel categoryAllPanel;
	private VerticalPanel keywordResultPanel;

	public GruopBuySearchViewImpl(EventBus eventBus, DispatchAsync dispatch) {
		// header
		VerticalPanel compositePanel = new VerticalPanel();
		compositePanel.setWidth("100%");
		header = new GroupBuySearchHeader();
		header.setWidth("100%");
		compositePanel.add(header);

		onlyTodayCheckBox = new CheckBox("今日发布");
		onlyTodayCheckBox.setStyleName("sort-right");
		compositePanel.add(onlyTodayCheckBox);

		categoryTabPanel = new TabLayoutPanel(40, Unit.PX);
		// slowly show
		categoryTabPanel.setAnimationDuration(50);
		categoryTabPanel.setWidth("100%");
		orderTypeTabPanelList = new HashMap<String, TabLayoutPanel>();
		pageListWidgetList = new HashMap<String, PageListWidget>();
		for (Category c : Category.getDisplayOrder()) {
			// build category panel
			HorizontalPanel tabInnerPanel = new HorizontalPanel();
			HorizontalPanel categoryResultPanel = new HorizontalPanel();
			tabInnerPanel.add(categoryResultPanel);
			if (c == Category.C_CATEGORY_ALL) {
				// init the search
				keywordResultPanel = new VerticalPanel();
				keywordResultPanel.setStyleName("keywordResultPanel-layout");
				tabInnerPanel.add(keywordResultPanel);

				keywordSearchPanel = new GroupBuyResultViewImpl();
				Widget resultViewWidget = keywordSearchPanel.asWidget();
				resultViewWidget.setHeight("3700px");
				GroupBuyKeywordResultViewPresenter presenter = new GroupBuyKeywordResultViewPresenter(
						keywordSearchPanel, eventBus);
				presenter.bind();
				keywordResultPanel.add(resultViewWidget);
				keywordPageList = new PageListWidget();
				keywordPageList.setStyleName("pageList-layout");
				keywordResultPanel.add(keywordPageList);
				categoryAllPanel = categoryResultPanel;
			}

			tabInnerPanel.setWidth("100%");
			tabInnerPanel.setBorderWidth(0);
			categoryResultPanel.setWidth("100%");
			categoryResultPanel.setBorderWidth(0);
			//
			TabLayoutPanel orderTypeTabPanel = new TabLayoutPanel(30, Unit.PX);
			orderTypeTabPanelList.put(c.name(), orderTypeTabPanel);
			ResizeLayoutPanel resizePanel = new ResizeLayoutPanel();
			resizePanel.setStyleName("orderTypeTabPanel-layout");
			resizePanel.setHeight("3700px");
			resizePanel.setWidth("100%");
			resizePanel.setWidget(orderTypeTabPanel);
			categoryResultPanel.add(resizePanel);
			categoryResultPanel
					.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			orderTypeTabPanel.setWidth("100%");
			for (OrderType orderType : OrderType.getDisplayOrder()) {
				// build result
				GroupBuyResultView resultView = new GroupBuyResultViewImpl();
				Widget resultViewWidget = resultView.asWidget();
				resultViewWidget.setHeight("3600px");
				GroupBuyResultViewPresenter presenter = new GroupBuyResultViewPresenter(
						resultView, eventBus, c, orderType);
				presenter.bind();
				VerticalPanel searchResultPanel = new VerticalPanel();
				// result
				searchResultPanel.add(resultViewWidget);
				// pager
				PageListWidget pageList = new PageListWidget();
				pageListWidgetList.put(orderType.getIdentify(c), pageList);
				// searchResultPanel
				// .setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
				searchResultPanel.add(pageList);
				pageList.setStyleName("pageList-layout");
				orderTypeTabPanel.add(searchResultPanel,
						orderType.getDisplayName());
			}
			categoryTabPanel.add(tabInnerPanel, c.getDisplayName());
		}
		ResizeLayoutPanel resizePanel = new ResizeLayoutPanel();
		resizePanel.setWidget(categoryTabPanel);
		resizePanel.setStyleName("categoryTabPanel-layout");
		compositePanel.add(resizePanel);

		initWidget(compositePanel);
	}

	public void updateModel() {
		header.updateModel();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public ListBox getCitySelects() {
		return header.getCitySeclect();
	}

	@Override
	public TextBox getSearchTextBox() {
		return header.getSearchTextBox();
	}

	@Override
	public Button getSearchButton() {
		return header.getSearchButton();
	}

	@Override
	public TabLayoutPanel getCategoryTabPanel() {
		return categoryTabPanel;
	}

	@Override
	public Map<String, TabLayoutPanel> getOrderTypeTabPanelList() {
		return orderTypeTabPanelList;
	}

	@Override
	public CheckBox getOnlyTodayCheckBox() {
		return onlyTodayCheckBox;
	}

	@Override
	public Map<String, PageListWidget> getPageListWidgetList() {
		return pageListWidgetList;
	}

	@Override
	public GroupBuyResultView getKeywordSearchPanel() {
		return keywordSearchPanel;
	}

	@Override
	public PageListWidget getKeywordPageList() {
		return keywordPageList;
	}

	@Override
	public HorizontalPanel getCategoryAllPanel() {
		return categoryAllPanel;
	}

	@Override
	public VerticalPanel getKeywordResultPanel() {
		return keywordResultPanel;
	}
}
