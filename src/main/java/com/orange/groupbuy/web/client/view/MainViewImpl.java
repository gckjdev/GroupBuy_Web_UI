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

public class MainViewImpl extends Composite implements MainView {

	@UiField
	GroupBuyHeaderPanel headerPanel;

	@UiField
	GroupBuyTabHeader tabHeader;

	public MainViewImpl(EventBus eventBus, DispatchAsync dispatchAsync) {
		initWidget(uiBinder.createAndBindUi(this));

		MyGroupViewImpl myGroupView = new MyGroupViewImpl(eventBus,
				dispatchAsync);
		MyGroupPresenter presenter = new MyGroupPresenter(myGroupView, eventBus);
		presenter.bind();
		tabHeader.getMyGroupView().add(presenter.getDisplay().asWidget());
		// TODO:sortViewImpl
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
