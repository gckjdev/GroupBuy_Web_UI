package com.orange.groupbuy.web.client.view;

import java.util.Map;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.orange.groupbuy.web.client.component.PageListWidget;

public interface GroupBuySearchView extends WidgetDisplay {

	ListBox getCitySelects();

	CheckBox getOnlyTodayCheckBox();

	TabLayoutPanel getCategoryTabPanel();

	Map<String, TabLayoutPanel> getOrderTypeTabPanelList();

	Map<String, PageListWidget> getPageListWidgetList();

	GroupBuyResultView getKeywordSearchPanel();

	PageListWidget getKeywordPageList();

	HorizontalPanel getCategoryAllPanel();

	HorizontalPanel getKeywordResultPanel();

	TextBox getSearchTextBox();

	Button getSearchButton();
}
