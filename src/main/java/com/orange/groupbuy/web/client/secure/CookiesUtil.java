package com.orange.groupbuy.web.client.secure;

import java.util.Date;

import com.google.gwt.user.client.Cookies;

public class CookiesUtil {

	private static final long DURATION = 1000 * 60 * 60 * 24 * 14; // remembering
															// login. 2 weeks

	public static void set(String key, String value) {
		Date expires = new Date(System.currentTimeMillis() + DURATION);
		Cookies.setCookie(key, value, expires, null, "/", false);

	}

	public static String get(String key) {
		return Cookies.getCookie(key);
	}
	
	public static void remove(String key) {
	    String keyValue = get(key);
	    if(keyValue != null && keyValue.length() >= 0)
	        Cookies.removeCookie(key);
    }
}
