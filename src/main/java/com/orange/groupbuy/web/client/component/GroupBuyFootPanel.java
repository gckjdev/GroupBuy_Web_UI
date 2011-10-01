package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class GroupBuyFootPanel extends Composite{

    private static GroupBuyFootPanelUiBinder uiBinder = GWT.create(GroupBuyFootPanelUiBinder.class);

    interface GroupBuyFootPanelUiBinder extends UiBinder<Widget, GroupBuyFootPanel> {
    }

    public GroupBuyFootPanel() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
