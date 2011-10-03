package com.orange.groupbuy.web.client.component;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.shared.FieldVerifier;

public class RegisterDialog extends DialogBox {
	private static RegisterDialogUiBinder uiBinder = GWT.create(RegisterDialogUiBinder.class);
	 interface RegisterDialogUiBinder extends UiBinder<Widget, RegisterDialog> {
	    }
    private EventBus eventBus;

    @UiField
    TextBox userName;
    
    @UiField
    PasswordTextBox password;
    
    @UiField
    PasswordTextBox passwordAgain;
    
    @UiField
    Button registerButton;
    
    @UiField
    Button cancelRegistButton;
    
    @UiField
    InlineHTML tips;
    
	public RegisterDialog(EventBus eventBus) {
		super(true, true);
		setText("注册");
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.eventBus = eventBus;
		setWidget(uiBinder.createAndBindUi(this));		
		registerButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				String name = userName.getText();
				String psw = password.getText();
				String pswAgain = passwordAgain.getText();
				if(!FieldVerifier.isValidName(name)){
					setTips("<label style=\"color:red\">用户名长度必须大于6</label>");
					return;
				}
				if(psw == null || psw.length()==0 || !psw.equals(pswAgain)){
					setTips("<label style=\"color:red\">密码输入不正确</label>");
					return;
				}
				RegisterOKEvent registOKEvent = new RegisterOKEvent();
				registOKEvent.setUserName(name);
				registOKEvent.setPassword(psw);
				RegisterDialog.this.eventBus.fireEvent(registOKEvent);
			}
		});
		
		cancelRegistButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent arg0) {
				setVisible(false);
			}
		});
		
		addCloseHandler(new CloseHandler<PopupPanel>() {
			
			@Override
			public void onClose(CloseEvent<PopupPanel> arg0) {
				clear();
			}
		});
	}
	
	public void setTips(String html){
		tips.setHTML(html);
	}
	

    @Override
    public void show() {
    	super.show();
        userName.setFocus(true);
    }
    
    @Override
    public void clear() {
    	userName.setText("");
        password.setText("");
        passwordAgain.setText("");
		setTips("");
    } 
	
	public static class RegisterOKEvent extends GwtEvent<RegisterOKEventHandler>{

		private static Type<RegisterOKEventHandler> TYPE;
		
		private String userName;
	    

		private String password;
	    
		
		public static Type<RegisterOKEventHandler> getType() {
	        return TYPE != null ? TYPE: (TYPE = new Type<RegisterOKEventHandler>());
	    }
		
		@Override
		protected void dispatch(RegisterOKEventHandler handler) {
			 handler.onEvent(this);
		}

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<RegisterOKEventHandler> getAssociatedType() {
			return getType();
		}

	    public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
	public static interface RegisterOKEventHandler extends EventHandler{

		void onEvent(RegisterOKEvent registerOKEvent);
		
	}	

}
