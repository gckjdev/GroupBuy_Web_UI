package com.orange.groupbuy.web.client.model;

import net.customware.gwt.dispatch.shared.Result;

public class UserInfo implements Result {
    
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
