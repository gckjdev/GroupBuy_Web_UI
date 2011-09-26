package com.orange.groupbuy.web.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LoginSuccessHandler extends EventHandler {

    public void onEvent(LoginSuccessEvent loginSuccessEvent);

}
