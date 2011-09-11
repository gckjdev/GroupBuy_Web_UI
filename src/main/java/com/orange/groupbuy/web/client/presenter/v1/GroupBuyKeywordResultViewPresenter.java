package com.orange.groupbuy.web.client.presenter.v1;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.orange.groupbuy.web.client.event.KeywordSearchEvent;
import com.orange.groupbuy.web.client.event.KeywordSearchHandler;
import com.orange.groupbuy.web.client.http.HttpClient;
import com.orange.groupbuy.web.client.http.HttpClient.Callback;
import com.orange.groupbuy.web.client.model.Criteria;
import com.orange.groupbuy.web.client.model.OperationType;
import com.orange.groupbuy.web.client.model.SearchResult;
import com.orange.groupbuy.web.client.view.v1.GroupBuyResultView;

public class GroupBuyKeywordResultViewPresenter extends
		WidgetPresenter<GroupBuyResultView> {

	public GroupBuyKeywordResultViewPresenter(GroupBuyResultView display,
			EventBus eventBus) {
		super(display, eventBus);
	}

	@Override
	protected void onBind() {
		registerHandler(eventBus.addHandler(KeywordSearchEvent.getType(),
				new KeywordSearchHandler() {

					@Override
					public void onRefresh(KeywordSearchEvent event) {
						// build criteria
						Criteria criteria = new Criteria();
						criteria.setCity(event.getCity());
						criteria.setOnlyToday(event.isOnlyToday());
						criteria.setPageSize(event.getPageSize());
						criteria.setStartRow(event.getStartRow());
						criteria.setKeyword(event.getKeyword());
						criteria.setOperationType(OperationType.KEYWORD_SEARCH);

						HttpClient.searchGroupBuyHandler(criteria,
								new Callback() {

									@Override
									public void updateModel(
											List<SearchResult> resultList) {
										getDisplay().updateModel(resultList);
									}
								});
					}

				}));
	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onRevealDisplay() {
		// TODO Auto-generated method stub

	}
}
