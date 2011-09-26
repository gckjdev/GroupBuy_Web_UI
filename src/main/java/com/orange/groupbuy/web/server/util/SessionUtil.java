package com.orange.groupbuy.web.server.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

	private static final ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<HttpSession>();

	public static void set(HttpSession value) {
		sessionThreadLocal.set(value);
	}

	public static HttpSession get() {
		return sessionThreadLocal.get();
	}
}
