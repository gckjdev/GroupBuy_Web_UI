package com.orange.groupbuy.web.client.presenter;

import java.util.ArrayList;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.secure.CookieSecureSessionAccessor;
import net.customware.gwt.dispatch.client.secure.SecureDispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.component.GroupBuyFootPanel;
import com.orange.groupbuy.web.client.component.GroupBuyHeaderPanel;
import com.orange.groupbuy.web.client.component.LoginDialog;
import com.orange.groupbuy.web.client.dispatch.GetCityNames;
import com.orange.groupbuy.web.client.dispatch.GetUser;
import com.orange.groupbuy.web.client.event.CityChangedEvent;
import com.orange.groupbuy.web.client.event.LoginSuccessEvent;
import com.orange.groupbuy.web.client.event.LoginSuccessHandler;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.model.CityNames;
import com.orange.groupbuy.web.client.model.Item;
import com.orange.groupbuy.web.client.model.UserInfo;
import com.orange.groupbuy.web.client.presenter.MainPresenter.MainView;
import com.orange.groupbuy.web.client.secure.CookiesUtil;
import com.orange.groupbuy.web.shared.UIConstatns;

public class MainPresenter extends WidgetPresenter<MainView> {

	private final DispatchAsync dispatch = new StandardDispatchAsync(
			new DefaultExceptionHandler());

	private final DispatchAsync secureDispatch = new SecureDispatchAsync(
			new DefaultExceptionHandler(), new CookieSecureSessionAccessor(
					UIConstatns.SESSION_COOKIE_NAME));
	public static interface MainView extends WidgetDisplay {
		TabLayoutPanel getTabHeader();

		GroupBuyHeaderPanel getHeaderPanel();
		
		GroupBuyFootPanel getFootPanel();

		ListBox getCitySelect();
		
		Anchor getLoginLink();
		
		Anchor getLogoutLink();
		
		Anchor getProfileLink();
		
		LoginDialog getLoginDialog();
	}



	public MainPresenter(MainView display, EventBus eventBus) {
		super(display, eventBus);
	}

	public MainPresenter(MainView display, EventBus eventBus,
			DispatchAsync dispatchAsync) {
		super(display, eventBus);
		
	}

	@Override
	protected void onBind() {
	    registerHandler(eventBus.addHandler(LoginSuccessEvent.getType(),
                new LoginSuccessHandler() {

                    @Override
                    public void onEvent(LoginSuccessEvent event) {
                        final String userName = event.getUserName();
                        dispatch.execute(new GetUser(event.getUserName(), event.getPassword()), 
                                new SimpleCallback<UserInfo>() {
                                    @Override
                                    public void onSuccess(UserInfo result) {
                                        if (result == null) {
                                            Window.alert("userInfo null");
											return;
                                        }
                                        String userId = result.getUserId();
                                        if(userId != null && userId.length() > 0) {
											CookiesUtil.set(UIConstatns.USER_ID,userId);
											
                                            getDisplay().getLoginDialog().hide();
                                            getDisplay().getProfileLink().setText(userName);
                                            getDisplay().getProfileLink().setVisible(true);
                                            getDisplay().getLogoutLink().setVisible(true);
                                            getDisplay().getLoginLink().setVisible(false);
                                        }
                                    }
                                });
                        
                    }
        }));
	    registerHandler(getDisplay().getLoginLink().addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                LoginDialog dialog = getDisplay().getLoginDialog();
                dialog.center();
                dialog.show();
            }
        }));
	    
	    registerHandler(getDisplay().getLogoutLink().addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                CookiesUtil.remove(UIConstatns.USER_ID);
                
                getDisplay().getLoginDialog().clear();
                getDisplay().getProfileLink().setVisible(false);
                getDisplay().getLogoutLink().setVisible(false);
                getDisplay().getLoginLink().setVisible(true);
                
            }
        }));
	            
		registerHandler(getDisplay().getCitySelect().addAttachHandler(
				new Handler() {

					@Override
					public void onAttachOrDetach(AttachEvent event) {
						dispatch.execute(new GetCityNames(),
								new SimpleCallback<CityNames>() {

									@Override
									public void onSuccess(CityNames result) {
										ArrayList<Item> cityList = result
												.getCityList();
										getDisplay().getCitySelect().clear();
										for (Item city : cityList) {
											getDisplay()
													.getCitySelect()
													.addItem(
															city.getDisplayName(),
															city.getValue());
										}
										// init the city
										initDefaultCitySelect();
									}
								});
					}
				}));

		registerHandler(getDisplay().getCitySelect().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						eventBus.fireEvent(new CityChangedEvent());
					}
				}));

		registerHandler(getDisplay().getTabHeader().addSelectionHandler(
				new SelectionHandler<Integer>() {

					@Override
					public void onSelection(SelectionEvent<Integer> event) {
						Integer selectItem = event.getSelectedItem();
						if (selectItem == 0) {
							// mygroup
						} else if (selectItem == 1) {
							//
						} else if (selectItem == 2) {

						}

						eventBus.fireEvent(new TabHeaderTabChangedEvent());
					}
				}));
	}

	private native String getAutoDetectedCity()/*-{
		return $wnd.autoDetectedCity;
	}-*/;

	private void initDefaultCitySelect() {
		String autoCity = getAutoDetectedCity();
		if (autoCity != null && !autoCity.isEmpty()) {
			int selectedIndex = getSelectedIndex(autoCity);
			if (selectedIndex != -1) {
				getDisplay().getCitySelect().setSelectedIndex(selectedIndex);
			}
		}
	}

	private int getSelectedIndex(String autoCity) {
		int selectedIndex = -1;
		int count = getDisplay().getCitySelect().getItemCount();
		for (int index = 0; index < count; index++) {
			String value = getDisplay().getCitySelect().getValue(index);
			if (!value.isEmpty()) {
				// TODO: some bug here.
				if (autoCity.contains(value)) {
					selectedIndex = index;
					break;
				}
			}
		}
		return selectedIndex;
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
