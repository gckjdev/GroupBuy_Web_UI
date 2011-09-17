package com.orange.groupbuy.web.server.handler;

import java.util.ArrayList;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.orange.groupbuy.web.client.dispatch.GetMyGroup;
import com.orange.groupbuy.web.client.model.Category;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.ItemList;

public class GetMyGroupHandler implements ActionHandler<GetMyGroup, ItemList> {

	@Override
	public ItemList execute(GetMyGroup arg0, ExecutionContext arg1)
			throws DispatchException {
		// TODO: get real data
		ArrayList<Item> categoryItems = new ArrayList<Item>();
		Category[] categoryList = Category.getDisplayOrder();
		for (Category c : categoryList) {
			Item item = new Item(String.valueOf(c.getValue()),
					c.getDisplayName());
			categoryItems.add(item);
		}

		ItemList result = new ItemList();
		result.setItems(categoryItems);
		return result;
	}

	@Override
	public Class<GetMyGroup> getActionType() {
		return GetMyGroup.class;
	}

	@Override
	public void rollback(GetMyGroup arg0, ItemList arg1, ExecutionContext arg2)
			throws DispatchException {
		// TODO Auto-generated method stub

	}

}
