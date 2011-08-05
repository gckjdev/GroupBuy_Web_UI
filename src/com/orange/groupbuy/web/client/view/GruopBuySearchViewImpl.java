package com.orange.groupbuy.web.client.view;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuySearchHeader;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.OrderType;
import com.orange.groupbuy.web.client.presenter.GroupBuyResultViewPresenter;

public class GruopBuySearchViewImpl extends Composite implements
		GroupBuySearchView {

	private GroupBuySearchHeader header;
	private CheckBox onlyTodayCheckBox;
	private TabLayoutPanel categoryTabPanel;
	private List<TabLayoutPanel> orderTypeTabPanelList;

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
		orderTypeTabPanelList = new ArrayList<TabLayoutPanel>();
		for (Category c : Category.getDisplayOrder()) {
			// build category panel
			HorizontalPanel tabInnerPanel = new HorizontalPanel();
			tabInnerPanel.setWidth("100%");
			tabInnerPanel.setBorderWidth(0);
			TabLayoutPanel orderTypeTabPanel = new TabLayoutPanel(30, Unit.PX);
			orderTypeTabPanelList.add(orderTypeTabPanel);
			ResizeLayoutPanel resizePanel = new ResizeLayoutPanel();
			resizePanel.setHeight("4096px");
			resizePanel.setWidth("100%");
			resizePanel.setWidget(orderTypeTabPanel);
			tabInnerPanel.add(resizePanel);
			tabInnerPanel
					.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			orderTypeTabPanel.setWidth("100%");

			for (OrderType orderType : OrderType.getDisplayOrder()) {
				// build result
				GroupBuyResultView resultView = new GroupBuyResultViewImpl();
				GroupBuyResultViewPresenter presenter = new GroupBuyResultViewPresenter(
						resultView, eventBus, dispatch, c, orderType);
				presenter.bind();
				VerticalPanel searchResultPanel = new VerticalPanel();
				searchResultPanel.add(resultView.asWidget());
				orderTypeTabPanel.add(searchResultPanel,
						orderType.getDisplayName());
			}
			categoryTabPanel.add(tabInnerPanel, c.getDisplayName());
		}
		ResizeLayoutPanel resizePanel = new ResizeLayoutPanel();
		resizePanel.setHeight("4096px");
		resizePanel.setWidth("100%");
		resizePanel.setWidget(categoryTabPanel);
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
	public TabLayoutPanel getCategoryTabPanel() {
		return categoryTabPanel;
	}

	@Override
	public List<TabLayoutPanel> getOrderTypeTabPanelList() {
		return orderTypeTabPanelList;
	}

	@Override
	public CheckBox getOnlyTodayCheckBox() {
		return onlyTodayCheckBox;
	}
}
