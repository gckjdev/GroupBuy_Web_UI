package com.orange.groupbuy.web.client.view;

import java.util.List;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public interface GroupBuySearchView extends WidgetDisplay {

	ListBox getCitySelects();

	CheckBox getOnlyTodayCheckBox();

	TabLayoutPanel getCategoryTabPanel();

	List<TabLayoutPanel> getOrderTypeTabPanelList();
}
