package com.orange.groupbuy.web.client.component;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.event.LoginSuccessEvent;
import com.orange.groupbuy.web.shared.FieldVerifier;

public class LoginDialog extends DialogBox {

    private static LoginDialogUiBinder uiBinder = GWT.create(LoginDialogUiBinder.class);

    interface LoginDialogUiBinder extends UiBinder<Widget, LoginDialog> {
    }

    private EventBus eventBus;
    
    @UiField
    TextBox userName;
    
    @UiField
    PasswordTextBox password;
    
    @UiField
    Button sumbitButton;
    
    public LoginDialog(EventBus eventBus) {
        super(true, true);
        setText("登录");
        setAnimationEnabled(true);
        setGlassEnabled(true);
        this.eventBus = eventBus;
        setWidget( uiBinder.createAndBindUi(this));
        
        sumbitButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                LoginSuccessEvent loginEvent = new LoginSuccessEvent();
                String name = userName.getText();
                String pwd = password.getText();
                if (!FieldVerifier.isValidName(name) || pwd.length() == 0) {
                    Window.alert("username or password not valid");
                    return;
                }
                loginEvent.setUserName(name);
                loginEvent.setPassword(pwd);
                LoginDialog.this.eventBus.fireEvent(loginEvent);
            }

        });
        
    }
    
    @Override
    public void clear() {
        userName.setText("");
        password.setText("");
    }
    
}
