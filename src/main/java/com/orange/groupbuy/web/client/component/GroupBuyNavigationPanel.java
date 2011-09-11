package com.orange.groupbuy.web.client.component;

import java.util.Arrays;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.orange.groupbuy.web.client.model.Category;

public class GroupBuyNavigationPanel extends Composite {

	private static GroupBuyNavigationPanelUiBinder uiBinder = GWT
			.create(GroupBuyNavigationPanelUiBinder.class);

	interface GroupBuyNavigationPanelUiBinder extends
			UiBinder<Widget, GroupBuyNavigationPanel> {
	}

	@UiField
	CollpaseBox categroyBox;

	@UiField
	CollpaseBox priceBox;

	public GroupBuyNavigationPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		//
		categroyBox.getName().setText("分类");
		final SelectionModel<Category> selectionModel = new MultiSelectionModel<Category>();
		Column<Category, Boolean> checkColumn = new Column<Category, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(Category object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};

		TextColumn<Category> nameColumn = new TextColumn<Category>() {
			@Override
			public String getValue(Category contact) {
				return contact.getDisplayName();
			}
		};
		CellTable<Category> categorySelection = new CellTable<Category>();
		categroyBox.getContent().add(categorySelection);
		categorySelection.addColumn(checkColumn);
		categorySelection.setColumnWidth(checkColumn, 2, Unit.PX);
		categorySelection.addColumn(nameColumn);
		Category[] categoryList = Category.getDisplayOrder();
		categorySelection.setRowData(0, Arrays.asList(categoryList));

		//
		priceBox.getName().setText("价格");
		CellTable<String> priceSelection = new CellTable<String>();
		priceBox.content.add(priceSelection);

		final SelectionModel<String> selectionModel2 = new MultiSelectionModel<String>();
		Column<String, Boolean> checkColumn2 = new Column<String, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(String object) {
				// Get the value from the selection model.
				return selectionModel2.isSelected(object);
			}
		};

		TextColumn<String> nameColumn2 = new TextColumn<String>() {
			@Override
			public String getValue(String contact) {
				return contact;
			}
		};
		priceSelection.addColumn(checkColumn2);
		priceSelection.addColumn(nameColumn2);
		String[] priceList = new String[] { "10元以下", "10元以上" };
		priceSelection.setRowData(0, Arrays.asList(priceList));
	}
}