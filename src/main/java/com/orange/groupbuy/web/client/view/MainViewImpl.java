package com.orange.groupbuy.web.client.view;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuyHeaderPanel;
import com.orange.groupbuy.web.client.component.GroupBuyTabHeader;
import com.orange.groupbuy.web.client.presenter.MainPresenter.MainView;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;
import com.orange.groupbuy.web.client.presenter.SortViewPresenter;
import com.orange.groupbuy.web.client.presenter.TodayViewPresenter;

public class MainViewImpl extends Composite implements MainView {

	@UiField
	GroupBuyHeaderPanel headerPanel;

	@UiField
	GroupBuyTabHeader tabHeader;

	public MainViewImpl(EventBus eventBus, DispatchAsync dispatchAsync) {
		initWidget(uiBinder.createAndBindUi(this));

		// my group buy
		initMyGroupView(eventBus, dispatchAsync);

		// sortViewImpl
		initSortView(eventBus, dispatchAsync);

		// today
		initTodayView(eventBus, dispatchAsync);
	}

	private void initTodayView(EventBus eventBus, DispatchAsync dispatchAsync) {
		MyGroupView todayView = new TodayViewImpl(eventBus, dispatchAsync);
		TodayViewPresenter todayViewPresenter = new TodayViewPresenter(
				todayView, eventBus);
		todayViewPresenter.bind();
		tabHeader.getTodayView()
				.add(todayViewPresenter.getDisplay().asWidget());
	}

	private void initSortView(EventBus eventBus, DispatchAsync dispatchAsync) {
		MyGroupView sortView = new SortViewImpl(eventBus, dispatchAsync);
		SortViewPresenter sortViewPresenter = new SortViewPresenter(sortView,
				eventBus);
		sortViewPresenter.bind();
		tabHeader.getSortView().add(
				sortViewPresenter.getDisplay().asWidget());
	}

	private void initMyGroupView(EventBus eventBus, DispatchAsync dispatchAsync) {
		MyGroupViewImpl myGroupView = new MyGroupViewImpl(eventBus,
				dispatchAsync);
		MyGroupPresenter myGroupViewPresenter = new MyGroupPresenter(
				myGroupView, eventBus);
		myGroupViewPresenter.bind();
		tabHeader.getMyGroupView().add(
				myGroupViewPresenter.getDisplay().asWidget());
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@UiTemplate("MainLayout.ui.xml")
	interface MainLayoutUiBinder extends UiBinder<Widget, MainViewImpl> {
	}

	private static MainLayoutUiBinder uiBinder = GWT
			.create(MainLayoutUiBinder.class);

	@Override
	public TabLayoutPanel getTabHeader() {
		return tabHeader.getTabHeader();
	}
}
