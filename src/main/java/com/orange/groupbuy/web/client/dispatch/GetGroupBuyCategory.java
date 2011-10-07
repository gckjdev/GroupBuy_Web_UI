package com.orange.groupbuy.web.client.dispatch;

import net.customware.gwt.dispatch.shared.Action;

import com.orange.groupbuy.web.client.model.ItemList;

public class GetGroupBuyCategory implements Action<ItemList> {
    
    private String city;
    
    public GetGroupBuyCategory() {
        
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GetGroupBuyCategory(String city) {
        this.city = city;
    }
    
}
