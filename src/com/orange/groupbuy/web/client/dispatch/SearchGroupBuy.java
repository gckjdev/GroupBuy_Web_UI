package com.orange.groupbuy.web.client.dispatch;

import net.customware.gwt.dispatch.shared.Action;

import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.SearchResultList;

public class SearchGroupBuy implements Action<SearchResultList> {

	private Criteria criteria;

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

}
