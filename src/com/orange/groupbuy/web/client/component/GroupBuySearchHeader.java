package com.orange.groupbuy.web.client.component;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.orange.groupbuy.web.client.Resources;

public class GroupBuySearchHeader extends Composite {

	private Image titleImage;
	private ListBox citySeclect;

	// private TextBox subscirbeText;

	// private Button subscirbeButton;

	// private PushButton sinaButton;

	public ListBox getCitySeclect() {
		return citySeclect;
	}

	public GroupBuySearchHeader() {

		HorizontalPanel compositePanel = new HorizontalPanel();

		// HorizontalPanel leftPanel = new HorizontalPanel();
		// compositePanel.add(leftPanel);
		// leftPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		titleImage = new Image(Resources.INSTANCE.companyTitle());
		compositePanel.add(titleImage);

		citySeclect = new ListBox();
		compositePanel.setSpacing(5);
		compositePanel.add(citySeclect);
		initWidget(compositePanel);

		// leftPanel.add(citySeclect);
		// right
		// HorizontalPanel rightPanel = new HorizontalPanel();
		// compositePanel.add(rightPanel);
		// rightPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		// subscirbeText = new TextBox();
		// subscirbeText.setTitle("è¾“å…¥emailè®¢é˜…å›¢è´­ä¿¡æ�¯");
		// rightPanel.add(subscirbeText);

		// subscirbeButton = new Button("è®¢é˜…");
		// rightPanel.add(subscirbeButton);

		// Label interestLable = new Label("å…³æ³¨æˆ‘ä»¬ï¼š");
		// rightPanel.add(interestLable);

		// sinaButton = new PushButton(new Image(Resources.INSTANCE.sina()));
		// rightPanel.add(sinaButton);
		// initWidget(compositePanel);

		// citySeclect = new ListBox();
		// RootPanel.get("citylist").add(citySeclect);

		// HorizontalPanel compositePanel = new HorizontalPanel();
		//
		// HorizontalPanel leftPanel = new HorizontalPanel();
		// compositePanel.add(leftPanel);
		// leftPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		// titleImage = new Image(Resources.INSTANCE.companyTitle());
		// leftPanel.add(titleImage);

		// citySeclect = new ListBox();
		// leftPanel.add(citySeclect);
		// right
		// HorizontalPanel rightPanel = new HorizontalPanel();
		// compositePanel.add(rightPanel);
		// rightPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		// subscirbeText = new TextBox();
		// subscirbeText.setTitle("è¾“å…¥emailè®¢é˜…å›¢è´­ä¿¡æ�¯");
		// rightPanel.add(subscirbeText);

		// subscirbeButton = new Button("è®¢é˜…");
		// rightPanel.add(subscirbeButton);

		// Label interestLable = new Label("å…³æ³¨æˆ‘ä»¬ï¼š");
		// rightPanel.add(interestLable);

		// sinaButton = new PushButton(new Image(Resources.INSTANCE.sina()));
		// rightPanel.add(sinaButton);
		// initWidget(compositePanel);
	}

	public void updateModel() {
		citySeclect.addItem("å¹¿å·ž", "GZ");
		citySeclect.addItem("åŒ—äº¬", "BJ");
	}
}
