package com.orange.groupbuy.web.client.view;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.GroupBuyNavigationPanel;
import com.orange.groupbuy.web.client.presenter.MyGroupPresenter.MyGroupView;

public class MyGroupViewImpl extends Composite implements MyGroupView {

	private static MyGroupLayoutUiBinder uiBinder = GWT
			.create(MyGroupLayoutUiBinder.class);

	@UiTemplate("MyGroupLayout.ui.xml")
	interface MyGroupLayoutUiBinder extends UiBinder<Widget, MyGroupViewImpl> {
	}

	@UiField
	GroupBuyNavigationPanel myGroupNavigationPanel;

	public MyGroupViewImpl(EventBus eventBus, DispatchAsync dispatchAsync) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
