package com.orange.groupbuy.web.server.handler;

import java.util.ArrayList;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.orange.groupbuy.web.client.dispatch.GetCityNames;
import com.orange.groupbuy.web.client.model.CityNames;
import com.orange.groupbuy.web.client.model.Item;

public class GetCityNamesHandler implements
		ActionHandler<GetCityNames, CityNames> {

	private static final String[] names = new String[] { "北京", "上海", "广州", "重庆" };

	@Override
	public CityNames execute(GetCityNames action, ExecutionContext context)
			throws DispatchException {
		ArrayList<Item> cityList = new ArrayList<Item>();

		cityList.add(new Item("", "全国"));
		for (String name : names) {
			cityList.add(new Item(name, name));
		}
		CityNames names = new CityNames();
		names.setCityList(cityList);
		return names;
	}

	@Override
	public Class<GetCityNames> getActionType() {
		return GetCityNames.class;
	}

	@Override
	public void rollback(GetCityNames arg0, CityNames arg1,
			ExecutionContext arg2) throws DispatchException {
		// nothing to do
	}

}
