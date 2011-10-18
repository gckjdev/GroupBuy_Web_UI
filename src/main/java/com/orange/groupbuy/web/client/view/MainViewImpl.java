package com.orange.groupbuy.web.client.view;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.component.CityWidget;
import com.orange.groupbuy.web.client.component.GroupBuyFootPanel;
import com.orange.groupbuy.web.client.component.GroupBuyHeaderPanel;
import com.orange.groupbuy.web.client.component.GroupBuyTabHeader;
import com.orange.groupbuy.web.client.component.LoginDialog;
import com.orange.groupbuy.web.client.component.RegisterDialog;
import com.orange.groupbuy.web.client.presenter.AbstractGroupBuyPresenter.GroupBuyView;
import com.orange.groupbuy.web.client.presenter.MainPresenter.MainView;
import com.orange.groupbuy.web.client.presenter.SearchViewPresenter;
import com.orange.groupbuy.web.client.presenter.TodayViewPresenter;
import com.orange.groupbuy.web.client.presenter.TopViewPresenter;

public class MainViewImpl extends Composite implements MainView {

	@UiField
	GroupBuyHeaderPanel headerPanel;

	@UiField
	GroupBuyTabHeader tabHeader;
	
	@UiField
    GroupBuyFootPanel footPanel;

	LoginDialog loginDialog ;
	
	RegisterDialog registerDialog;
	
	CityWidget cityWidget;
	
	@UiField
	Panel mainPanel;

//	@UiField
//	LayoutPanelLayer groupbuyPanel;
	
	public MainViewImpl(EventBus eventBus, DispatchAsync dispatchAsync) {
		initWidget(uiBinder.createAndBindUi(this));
		
		loginDialog = new LoginDialog(eventBus);
		
		registerDialog = new RegisterDialog(eventBus);
		
		cityWidget = new CityWidget(eventBus);
		cityWidget.setVisible(false);
		
		getProfileLink().setVisible(false);
		getLogoutLink().setVisible(false);
		
		
		// my group buy
        // initMyGroupView(eventBus, dispatchAsync);

        // top view
        initTopView(eventBus);

        // today
        initTodayView(eventBus);
        
		// search
		initSearchView(eventBus);
		
		

//		setTabHeight(4200);
		
	}


	private void initTodayView(EventBus eventBus) {
		GroupBuyView todayView = new TodayViewImpl(eventBus,getCityWidget());
		TodayViewPresenter todayViewPresenter = new TodayViewPresenter(
				todayView, eventBus);
		todayViewPresenter.bind();
		tabHeader.getTodayView()
				.add(todayViewPresenter.getDisplay().asWidget());
	}

	private void initTopView(EventBus eventBus) {
		GroupBuyView topView = new TopViewImpl(eventBus, getCityWidget());
		TopViewPresenter topViewPresenter = new TopViewPresenter(topView,
				eventBus);
		topViewPresenter.bind();
		tabHeader.getSortView().add(
				topViewPresenter.getDisplay().asWidget());
	}

	private void initSearchView(EventBus eventBus) {
		GroupBuyView view = new SearchViewImpl(eventBus, getCityWidget(),
				getSearchBox());
		SearchViewPresenter presenter = new SearchViewPresenter(view, eventBus);
		presenter.bind();
		tabHeader.getSearchView().add(presenter.getDisplay().asWidget());
	}

	// private void initMyGroupView(EventBus eventBus, DispatchAsync
	// dispatchAsync) {
	// MyGroupViewImpl myGroupView = new MyGroupViewImpl(eventBus,
	// dispatchAsync);
	// MyGroupPresenter myGroupViewPresenter = new MyGroupPresenter(
	// myGroupView, eventBus);
	// myGroupViewPresenter.bind();
	// tabHeader.getMyGroupView().add(
	// myGroupViewPresenter.getDisplay().asWidget());
	// }

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

	@Override
	public GroupBuyHeaderPanel getHeaderPanel() {
		return headerPanel;
	}

//	@Override
//	public ListBox getCitySelect() {
//		return headerPanel.getCitySelect();
//	}

	@Override
	public TextBox getSearchBox() {
		return headerPanel.getSearchBox();
	}

    @Override
    public Anchor getLoginLink() {
        return headerPanel.getLoginLink();
    }
    
    @Override
    public Anchor getRegisterLink(){
    	return headerPanel.getRegisterLink();
    }
    
    @Override
    public Anchor getProfileLink() {
        return headerPanel.getProfileLink();
    }


    @Override
    public Anchor getLogoutLink() {
        return headerPanel.getLogoutLink();
    }

    @Override
    public LoginDialog getLoginDialog() {
        return loginDialog;
    }

	@Override
	public RegisterDialog getRegisterDialog() {
		return registerDialog;
	}
    @Override
    public GroupBuyFootPanel getFootPanel() {
        return footPanel;
    }
    
    @Override
    public CityWidget getCityWidget() {
        return cityWidget;
    }

    @Override
    public Anchor getCityLink() {
        return headerPanel.getCityLink();
    }

	@Override
	public FocusWidget getSearchSubmit() {
		return headerPanel.getSearchSubmit();
	}


	@Override
	public Anchor getCurrCity() {
		return headerPanel.getCurrCity();
	}


	@Override
	public void setTabHeight(int h) {
		mainPanel.setHeight(157 + 45 + 200 + h + "px");
//		tabHeader.setHeight(h + "px");
//		groupbuyPanel.setTopHeight(130, Unit.PX, 130 + h, Unit.PX);
	}
}
