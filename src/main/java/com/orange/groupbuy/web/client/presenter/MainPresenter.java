package com.orange.groupbuy.web.client.presenter;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.secure.CookieSecureSessionAccessor;
import net.customware.gwt.dispatch.client.secure.SecureDispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.orange.groupbuy.web.client.SimpleCallback;
import com.orange.groupbuy.web.client.component.CityWidget;
import com.orange.groupbuy.web.client.component.GroupBuyFootPanel;
import com.orange.groupbuy.web.client.component.GroupBuyHeaderPanel;
import com.orange.groupbuy.web.client.component.LoginDialog;
import com.orange.groupbuy.web.client.component.RegisterDialog;
import com.orange.groupbuy.web.client.component.RegisterDialog.RegisterOKEvent;
import com.orange.groupbuy.web.client.dispatch.GetUser;
import com.orange.groupbuy.web.client.dispatch.RegisterEmail;
import com.orange.groupbuy.web.client.event.CityChangedEvent;
import com.orange.groupbuy.web.client.event.CityChangedHandler;
import com.orange.groupbuy.web.client.event.LoginSuccessEvent;
import com.orange.groupbuy.web.client.event.LoginSuccessHandler;
import com.orange.groupbuy.web.client.event.ResizeMainEvent;
import com.orange.groupbuy.web.client.event.ResizeMainHandler;
import com.orange.groupbuy.web.client.event.TabHeaderTabChangedEvent;
import com.orange.groupbuy.web.client.event.v1.KeywordSearchEvent;
import com.orange.groupbuy.web.client.model.UserInfo;
import com.orange.groupbuy.web.client.presenter.MainPresenter.MainView;
import com.orange.groupbuy.web.client.secure.CookiesUtil;
import com.orange.groupbuy.web.client.view.MainViewImpl;
import com.orange.groupbuy.web.shared.ErrorCode;
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
		
		Anchor getLoginLink();
		
		Anchor getLogoutLink();
		
		Anchor getProfileLink();
		
		Anchor getCityLink();
		
		Anchor getCurrCity();
		
		Anchor getRegisterLink();
		
		LoginDialog getLoginDialog();

		RegisterDialog getRegisterDialog();
		
		CityWidget getCityWidget();
		
		TextBox getSearchBox();

		FocusWidget getSearchSubmit();
		

		void setTabHeight(int h);
	}

	private int currentTabIndex = 0;

	public MainPresenter(MainView display, EventBus eventBus) {
		super(display, eventBus);
	}

	public MainPresenter(MainView display, EventBus eventBus,
			DispatchAsync dispatchAsync) {
		super(display, eventBus);
		
	}
	
	private void loadCookies() {
	    String userName = CookiesUtil.get(UIConstatns.USER_NAME);
	    if(userName != null && userName.length() > 0) {
	        getDisplay().getProfileLink().setText(userName);
	        getDisplay().getLoginLink().setVisible(false);
	        getDisplay().getRegisterLink().setVisible(false);
	        getDisplay().getLogoutLink().setVisible(true);
	        getDisplay().getProfileLink().setVisible(true);
	        
	    }
	}

	@Override
	protected void onBind() {
	    loadCookies();
	    
	    registerHandler(eventBus.addHandler(CityChangedEvent.getType(),
                new CityChangedHandler() {

                    @Override
                    public void onChanged(CityChangedEvent event) {
                        getDisplay().getCityWidget().hide();
                        getDisplay().getCityWidget().setVisible(false);
                        getDisplay().getCurrCity().setText(event.getCityName());
                    }
                }));
	    
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
//                                    	Window.alert(result.toString());
                                        String userId = result.getUserId();
                                        if(userId != null && userId.length() > 0) {
											CookiesUtil.set(UIConstatns.USER_ID,userId);
											CookiesUtil.set(UIConstatns.USER_NAME,userName);
                                            getDisplay().getLoginDialog().hide();
                                            getDisplay().getProfileLink().setText(userName);
                                            getDisplay().getProfileLink().setVisible(true);
                                            getDisplay().getLogoutLink().setVisible(true);
                                            getDisplay().getLoginLink().setVisible(false);
                                            getDisplay().getRegisterLink().setVisible(false);
                                        }
                                    }
                                });
                        
                    }
        }));
	    registerHandler(eventBus.addHandler(RegisterOKEvent.getType(),
                new RegisterDialog.RegisterOKEventHandler() {

                    @Override
                    public void onEvent(final RegisterOKEvent event) {
                        dispatch.execute(new RegisterEmail(event.getUserName(), event.getPassword()), 
                                new SimpleCallback<UserInfo>() {
                                    @Override
                                    public void onSuccess(UserInfo result) {
                                        if (result == null) {
                                            Window.alert("userInfo null");
											return;
                                        }
                                    	String userId = result.getUserId();
                                    	String errcode = result.getRtCode();
                                    	String errstr = null;

                                    	if(userId != null){
											getDisplay()
													.getRegisterDialog()
													.setTips(
															"<label style=\"color:green\">注册成功，正在登陆....</label>");
	                                        
	                                        getDisplay().getRegisterDialog().setVisible(false);
	                                        LoginSuccessEvent loginEvent = new LoginSuccessEvent();
	                                        loginEvent.setUserName(event.getUserName());
	                                        loginEvent.setPassword(event.getPassword());
	                                        eventBus.fireEvent(loginEvent);
                                    	}else if(errcode != null && errcode.length() > 0){
                                    		int ERR = Integer.parseInt(errcode);
                                    		switch(ERR){
											case ErrorCode.ERROR_EMAIL_NOT_VALID:
												errstr = "邮箱格式不正确，请重新输入";
												break;
											case ErrorCode.ERROR_EMAIL_EXIST:
												errstr = "邮箱已存在，请直接登录";
												break;
                                    			case ErrorCode.ERROR_CREATE_USER: 
											default:
												errstr = "系统创建用户失败，请稍等后重新注册";
												break;
                                    		}
                                    		getDisplay().getRegisterDialog().setTips("<label style=\"color:red\">" + errstr + "</label>");
                                    		return;
                                    	}
                                    }
                                });
                        
                    }
        }));

        registerHandler(getDisplay().getCityLink().addAttachHandler(new Handler() {

            @Override
            public void onAttachOrDetach(AttachEvent arg0) {
                String autoCity = getAutoDetectedCity();
                if (autoCity != null && !autoCity.isEmpty()) {
                    String city = autoCity.substring(0,autoCity.length()-1);
                    getDisplay().getCurrCity().setText(city);
                }
            }

        }));
  
	    registerHandler(getDisplay().getCityLink().addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                CityWidget cityWidget = getDisplay().getCityWidget();
                if(cityWidget.isVisible()) {
                    cityWidget.setVisible(false);
                    cityWidget.hide();
                }
                else {
                    cityWidget.setVisible(true);
                    cityWidget.show();
                }
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
	    
	    registerHandler(getDisplay().getRegisterLink().addClickHandler(new ClickHandler() {
			
				@Override
				public void onClick(ClickEvent event) {
					RegisterDialog dialog = getDisplay().getRegisterDialog();
					dialog.center();
	                dialog.show();
				}
			})
		);
	    registerHandler(getDisplay().getLogoutLink().addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                CookiesUtil.remove(UIConstatns.USER_ID);
                CookiesUtil.remove(UIConstatns.USER_NAME);
                getDisplay().getLoginDialog().clear();
                getDisplay().getProfileLink().setVisible(false);
                getDisplay().getLogoutLink().setVisible(false);
                getDisplay().getLoginLink().setVisible(true);
                getDisplay().getRegisterLink().setVisible(true);
                
            }
        }));
	            
	        //have replaced the city select box with citywidget
//		registerHandler(getDisplay().getCitySelect().addAttachHandler(
//				new Handler() {
//
//					@Override
//					public void onAttachOrDetach(AttachEvent event) {
//						dispatch.execute(new GetCityNames(),
//								new SimpleCallback<CityNames>() {
//
//									@Override
//									public void onSuccess(CityNames result) {
//										ArrayList<Item> cityList = result
//												.getCityList();
//										getDisplay().getCitySelect().clear();
//										for (Item city : cityList) {
//											getDisplay()
//													.getCitySelect()
//													.addItem(
//															city.getDisplayName(),
//															city.getValue());
//										}
//										// init the city
//										initDefaultCitySelect();
//
//										// // init execute query
//										eventBus.fireEvent(new CityChangedEvent());
//										// TODO: work around for IE
//										getDisplay().getTabHeader()
//												.selectTab(1);
//										getDisplay().getTabHeader()
//												.selectTab(0);
//									}
//								});
//
//					}
//				}));
//
//		registerHandler(getDisplay().getCitySelect().addChangeHandler(
//				new ChangeHandler() {
//
//					@Override
//					public void onChange(ChangeEvent event) {
//						eventBus.fireEvent(new CityChangedEvent());
//					}
//				}));

		registerHandler(getDisplay().getTabHeader().addSelectionHandler(
				new SelectionHandler<Integer>() {

					@Override
					public void onSelection(SelectionEvent<Integer> event) {
						Integer selectItem = event.getSelectedItem();
						currentTabIndex = selectItem;
						if(selectItem <3){							
							eventBus.fireEvent(new TabHeaderTabChangedEvent(selectItem));
						}else{
							getDisplay().setTabHeight(700);
						}
					}
				}));

		registerHandler(getDisplay().getSearchSubmit().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent arg0) {
						// TODO: select the right tab
						getDisplay().getTabHeader().selectTab(2);
						eventBus.fireEvent(new KeywordSearchEvent());
					}
				}));
		
		registerHandler(eventBus.addHandler(ResizeMainEvent.getType(), new ResizeMainHandler() {			
			@Override
			public void resize(ResizeMainEvent eve) {
				if(eve.getTabIndex() == currentTabIndex)
					getDisplay().setTabHeight(eve.getHeight());
			}
		}));
	}

	private native String getAutoDetectedCity()/*-{
		return $wnd.autoDetectedCity;
	}-*/;

//	private void initDefaultCitySelect() {
//		String autoCity = getAutoDetectedCity();
//		if (autoCity != null && !autoCity.isEmpty()) {
//			int selectedIndex = getSelectedIndex(autoCity);
//			if (selectedIndex != -1) {
//				getDisplay().getCitySelect().setSelectedIndex(selectedIndex);
//			}
//		}
//	}
//
//	private int getSelectedIndex(String autoCity) {
//		int selectedIndex = -1;
//		int count = getDisplay().getCitySelect().getItemCount();
//		for (int index = 0; index < count; index++) {
//			String value = getDisplay().getCitySelect().getValue(index);
//			if (!value.isEmpty()) {
//				// TODO: some bug here.
//				if (autoCity.contains(value)) {
//					selectedIndex = index;
//					break;
//				}
//			}
//		}
//		return selectedIndex;
//	}

	@Override
	protected void onUnbind() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onRevealDisplay() {
		// TODO Auto-generated method stub
	}

}
