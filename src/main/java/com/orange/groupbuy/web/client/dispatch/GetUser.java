package com.orange.groupbuy.web.client.dispatch;

import net.customware.gwt.dispatch.shared.Action;

import com.orange.groupbuy.web.client.model.UserInfo;

public class GetUser implements Action<UserInfo> {

    private String userName;
    
    private String password;
    
    public GetUser() {
        
    }

    public GetUser(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
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
