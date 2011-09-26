package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoginSuccessEvent extends GwtEvent<LoginSuccessHandler> {

    private static Type<LoginSuccessHandler> TYPE;
    
    private String userName;
    
    private String userId;
    
    private String password;
    
    public static Type<LoginSuccessHandler> getType() {
        return TYPE != null ? TYPE: (TYPE = new Type<LoginSuccessHandler>());
    }
    
    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<LoginSuccessHandler> getAssociatedType() {
       return getType();
    }

    @Override
    protected void dispatch(LoginSuccessHandler handler) {
        handler.onEvent(this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
