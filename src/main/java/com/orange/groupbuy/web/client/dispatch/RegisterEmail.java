package com.orange.groupbuy.web.client.dispatch;

import com.orange.groupbuy.web.client.model.UserInfo;

import net.customware.gwt.dispatch.shared.Action;

public class RegisterEmail implements Action<UserInfo> {

    private String email;
    
    private String password;
    
    public RegisterEmail() {
        
    }

    public RegisterEmail(String userName, String password) {
        super();
        this.email = userName;
        this.password = password;
    }


    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
