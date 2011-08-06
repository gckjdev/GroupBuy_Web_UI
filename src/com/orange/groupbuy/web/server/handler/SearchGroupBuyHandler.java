package com.orange.groupbuy.web.server.handler;

import java.util.ArrayList;
import java.util.Random;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.orange.groupbuy.web.client.dispatch.SearchGroupBuy;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.model.SearchResultList;

public class SearchGroupBuyHandler implements
		ActionHandler<SearchGroupBuy, SearchResultList> {

	Random random = new Random();

	@Override
	public SearchResultList execute(SearchGroupBuy action,
			ExecutionContext context)
			throws DispatchException {
		int resultCount = 0;
		while (resultCount == 0) {
			resultCount = random.nextInt(30);
		}
		// TODO : mock action only
		Criteria criteria = action.getCriteria();

		SearchResultList resultList = new SearchResultList();
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();
		resultList.setResults(results);
		for (int i = 0; i < resultCount; i++) {

			SearchResult result = new SearchResult();
			result.setBought(10);
			result.setCategory("[美食]");
			result.setDesctiption("仅76元！可莱丝鱼子酱修复精华针剂面膜，用丝滑的纤维素面膜片，鱼子酱萃取物，使用世界三大美味之");
			result.setDetailsId("details id");
			result.setImageUrl("http://img5.hao123.com/data/2_02feea550c032dc9dfa6990946a61738");
			result.setPrice(5d);
			result.setRebate(0.5);
			result.setSiteName("美团网");
			result.setSiteUrl("http://www.jumei.com");
			result.setValue(1d);
			
			result.setProductUrl("http://www.jumei.com/i/deal/fxzhijy0804.html");
			// result.setImageClickUrl("http://www.jumei.com/i/deal/fxzhijy0804.html");
			results.add(result);
		}
		return resultList;
	}

	@Override
	public Class<SearchGroupBuy> getActionType() {
		return SearchGroupBuy.class;
	}

	@Override
	public void rollback(SearchGroupBuy action, SearchResultList result,
			ExecutionContext context) throws DispatchException {
		// TODO Auto-generated method stub
	}

}
