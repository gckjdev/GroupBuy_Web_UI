package com.orange.groupbuy.web.client.component;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class CollapseBox extends Composite {

	Label name;

	LayoutPanel content;

	@UiConstructor
	public CollapseBox(int size) {
		LayoutPanel dockPanel = new LayoutPanel();
		dockPanel.addStyleName("nav-box");
		name = new Label();
		name.addStyleName("nav-header");
		dockPanel.add(name);
		content = new LayoutPanel();
		dockPanel.add(content);
		content.addStyleName("nav-content");

		int nameHeight = 35;
		dockPanel.setWidgetTopHeight(name, 0, Unit.PX, nameHeight, Unit.PX);
		dockPanel.setWidgetTopHeight(content, nameHeight, Unit.PX, size, Unit.PX);
		initWidget(dockPanel);
	}

	public Label getName() {
		return name;
	}

	public LayoutPanel getContent() {
		return content;
	}

	@SuppressWarnings("unchecked")
	public <T> CellTable<T> getContentCellTable() {
		return (CellTable<T>) content.getWidget(0);
	}
}
