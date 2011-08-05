package com.orange.groupbuy.web.client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 1=餐饮美食，2=休闲娱乐，3=美容化妆，4=网上购物，5=运动健身,
 * 
 * @author echnlee
 * 
 */
public enum Category {
	C_CATEGORY_ALL(-1, "全部团购"), C_CATEGORY_UNKNOWN(0, "其他分类"), C_CATEGORY_EAT(
			1, "餐饮美食"), C_CATEGORY_FUN(2, "休闲娱乐"), C_CATEGORY_FACE(3, "美容化妆"), C_CATEGORY_SHOPPING(
			4, "网上购物"), C_CATEGORY_KEEPFIT(5, "运动健身"), C_CATEGORY_LIFE(6,
			"生活服务");

	private int value;
	private String displayName;

	Category(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public int getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Category[] getDisplayOrder() {
		Category[] values = Category.values();
		List<Category> list = new ArrayList<Category>();
		for (int i = 0; i < values.length; i++) {
			list.add(values[i]);
		}
		list.remove(C_CATEGORY_UNKNOWN);
		list.add(C_CATEGORY_UNKNOWN);
		Category[] result = new Category[values.length];
		return list.toArray(result);
	}
}
