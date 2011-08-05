package com.orange.groupbuy.web.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.orange.groupbuy.web.client.model.SearchResult;

public class GroupBuyWidget extends Widget {

	private static final String TARGET_BLANK = "_blank";

	private static GroupBuyWidgetUiBinder uiBinder = GWT
			.create(GroupBuyWidgetUiBinder.class);

	interface GroupBuyWidgetUiBinder extends
			UiBinder<DivElement, GroupBuyWidget> {
	}

	@UiField
	AnchorElement siteNameLabel;

	@UiField
	AnchorElement link;

	@UiField
	AnchorElement imageAnchor;

	@UiField
	ImageElement image;

	@UiField
	Element originalPriceLabel;

	@UiField
	SpanElement rebateLabel;

	@UiField
	Element boughtNumLabel;

	@UiField
	DivElement valueLabel;

	@UiField
	AnchorElement detailsButton;

	public GroupBuyWidget() {
		setElement(uiBinder.createAndBindUi(this));
	}

	public void updateModel(SearchResult result) {
		if (result != null) {
			siteNameLabel.setTarget(TARGET_BLANK);
			siteNameLabel.setHref(result.getSiteUrl());
			siteNameLabel.setInnerText(result.getSiteName());
			siteNameLabel.setClassName("merchant");
			// siteNameLabel
			// .setHTML("<a target=\"_blank\"href=\"http://www.jumei.com/\"class=\"merchant\">聚美优品</a>");

			link.setTitle(result.getDesctiption());
			link.setTarget(TARGET_BLANK);
			link.setHref(result.getProductUrl());
			link.setInnerHTML(result.getDesctiption());
			// link.setHTML("<a title=\"仅售59元！原价158元，梵希陀珐琅明色24色限量版灵动炫色心情指彩指甲油套装,指尖上的彩色笔，让你在美甲的同时找到艺术家般的灵感，做个百变美人!买2包邮！抓紧抢购吧！\" target=\"_blank\" href=\"http://www.jumei.com/i/deal/fxzhijy0804.html\">仅售59元！原价158元，梵希陀珐琅明色24色限量版灵动炫色心情指彩指甲油套装,指尖上的彩色笔，让你在美甲的同时找到艺术家般的灵感，做个百变美人!买2包邮！抓紧抢购吧！</a>");
			//
			imageAnchor.setTarget(TARGET_BLANK);
			imageAnchor.setHref(result.getProductUrl());
			imageAnchor.setClassName("good-img-a");

			image.setSrc(result.getImageUrl());
			// image.setHTML("<a target=\"_blank\" href=\"http://www.jumei.com/i/deal/fxzhijy0804.html?tn=baidutuan_tg&amp;baiduid=F0BFC693BE978E3E4879EE2C14E1A958\" class=\"good-img-a\"><img width=\"266\" height=\"166\"  src=\"http://img5.hao123.com/data/2_4e7575ffbb842100100e7e5be2c1c553\" alt=\"仅售59元！原价158元，梵希陀珐琅明色24色限量版灵动炫色心情指彩指甲油套装,指尖上的彩色笔，让你在美甲的同时找到艺术家般的灵感，做个百变美人!买2包邮！抓紧抢购吧！\" class=\"good-img\"></a>");

			//
			// originalPriceLabel.setHTML("<del>￥158</del>");
			//
			originalPriceLabel.setInnerText(formatMoney(result.getPrice()));
			rebateLabel.setInnerText(formatRebate(result.getRebate()));
			//
			boughtNumLabel.setInnerText(formatNumber(result.getBought()));
			// boughtNumLabel.setHTML("<b>2499</b>");
			//
			// valueLabel.setHTML("58");
			valueLabel.setInnerText(formatMoney(result.getValue()));

			detailsButton.setHref(result.getProductUrl());

			//
			// detailsButton
			// .setHTML("<a target=\"_blank\" href=\"http://www.jumei.com/i/deal/fxzhijy0804.html?tn=baidutuan_tg&amp;baiduid=F0BFC693BE978E3E4879EE2C14E1A958\" class=\"gosite\"></a>");

			// updateLabelValue(siteNameLabel, result.getSiteName());
			// link.setText(result.getDesctiption());
			// // TODO: url link
			// image.setUrl(result.getImage());
			// updateLabelValue(originalPriceLabel,
			// formatMoney(result.getPrice()));
			// updateLabelValue(rebateLabel, formatRebate(result.getRebate()));
			// updateLabelValue(boughtNumLabel,
			// formatNumber(result.getBought()));
			// updateLabelValue(valueLabel, formatMoney(result.getValue()));
		}
	}

	private String formatNumber(Integer result) {
		return String.valueOf(result);
	}

	private String formatRebate(Double result) {
		return String.valueOf(result * 10) + "折";
	}

	private String formatMoney(Double result) {
		return "￥" + String.valueOf(result);
	}

	private void updateLabelValue(Label label, String value) {
		label.setText(value);
	}
}
