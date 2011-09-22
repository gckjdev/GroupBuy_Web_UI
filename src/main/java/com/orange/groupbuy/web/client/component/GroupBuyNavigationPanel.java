package com.orange.groupbuy.web.client.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.PriceItem;

public class GroupBuyNavigationPanel extends Composite {

	public static interface SelectionCallback {
		void onSelectionChange(MultiSelectionModel<Item> selectionModel);
	}

	private static GroupBuyNavigationPanelUiBinder uiBinder = GWT
			.create(GroupBuyNavigationPanelUiBinder.class);

	interface GroupBuyNavigationPanelUiBinder extends
			UiBinder<Widget, GroupBuyNavigationPanel> {
	}

	@UiField
	DockLayoutPanel navigationPanel;

	@UiField
	CollpaseBox categroyBox;

	@UiField
	CollpaseBox myGroupBox;

	@UiField
	CollpaseBox priceBox;

	@UiFactory
	CollpaseBox createCollpaseBox(int size) {
		return new CollpaseBox(size);
	}

	public GroupBuyNavigationPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		initSignleSelection("分类", categroyBox);

		initSignleSelection("我的团购", myGroupBox);

		CellTable<Item> categoryCellTable = initMultipleSelection("价格",
				priceBox);

		List<Item> priceList = new ArrayList<Item>();
		String[] priceResult = new String[] { "10元以下", "10元以上" };
		String[] priceResultValue = new String[] { "-1,10", "10,100000" };
		for (int i = 0; i < priceResult.length; i++) {
			Item item = new Item(priceResultValue[i], priceResult[i]);
			priceList.add(item);
		}

		categoryCellTable.setRowData(0, priceList);
	}

	private CellTable<Item> initMultipleSelection(String name,
			CollpaseBox multipleSelection) {
		multipleSelection.getName().setText(name);
		final MultiSelectionModel<Item> selectionModel = new MultiSelectionModel<Item>();
		Column<Item, Boolean> checkColumn = new Column<Item, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(Item object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};

		TextColumn<Item> nameColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item contact) {
				return contact.getDisplayName();
			}
		};
		CellTable<Item> selection = new CellTable<Item>();
		multipleSelection.getContent().add(selection);
		selection.addColumn(checkColumn);
		selection.setColumnWidth(checkColumn, 2, Unit.PX);
		selection.addColumn(nameColumn);

		selection.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<Item> createCheckboxManager());
		return selection;
	}

	private CellTable<Item> initSignleSelection(String name,
			CollpaseBox multipleSelection) {
		multipleSelection.getName().setText(name);
		final SingleSelectionModel<Item> selectionModel = new SingleSelectionModel<Item>();
		Column<Item, Boolean> checkColumn = new Column<Item, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(Item object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};

		TextColumn<Item> nameColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item contact) {
				return contact.getDisplayName();
			}
		};
		CellTable<Item> selection = new CellTable<Item>();
		multipleSelection.getContent().add(selection);
		selection.addColumn(checkColumn);
		selection.setColumnWidth(checkColumn, 2, Unit.PX);
		selection.addColumn(nameColumn);

		selection.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<Item> createCheckboxManager());
		return selection;
	}

	public CollpaseBox getCategroyBox() {
		return categroyBox;
	}

	public CollpaseBox getPriceBox() {
		return priceBox;
	}

	public CollpaseBox getMyGroupBox() {
		return myGroupBox;
	}

	public DockLayoutPanel getNavigationPanel() {
		return navigationPanel;
	}

	public ArrayList<String> getSelectedCategoryList() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		SingleSelectionModel<Item> myGroupSelected = (SingleSelectionModel) this
				.getCategroyBox().getContentCellTable().getSelectionModel();
		ArrayList<String> categoryList = new ArrayList<String>();
		Item item = myGroupSelected.getSelectedObject();
		if (item != null) {
			categoryList.add(item.getValue());
		}
		// for (Item item : categorySet) {
		// categoryList.add(item.getValue());
		// }
		return categoryList;
	}

	public PriceItem getSelectedPrice() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		MultiSelectionModel<Item> priceSelected = (MultiSelectionModel) this
				.getPriceBox().getContentCellTable().getSelectionModel();
		//
		Set<Item> priceSet = priceSelected.getSelectedSet();
		int startPrice = -1;
		int endPrice = 100000;
		if (!priceSet.isEmpty()) {
			// use the range if there's
			// price selected.
			startPrice = 100000;
			endPrice = -1;
			for (Item item : priceSet) {
				String priceRange = item.getValue();
				String[] priceArray = priceRange.split(",");
				int start = Integer.parseInt(priceArray[0]);
				int end = Integer.parseInt(priceArray[1]);

				if (start < startPrice) {
					startPrice = start;
				}
				if (end > endPrice) {
					endPrice = end;
				}
			}
		}
		// now the statPrice and end
		// price is the range;

		PriceItem item = new PriceItem();
		item.setMin(String.valueOf(startPrice));
		item.setMax(String.valueOf(endPrice));
		return item;
	}
}