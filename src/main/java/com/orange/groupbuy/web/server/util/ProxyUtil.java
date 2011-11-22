package com.orange.groupbuy.web.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ProxyUtil {

	private static final String PROPERTY_API_SERVER = "groupbuy.api.server";
	// private static final String DEFAULT_API_SERVER =
	// "http://www.dipan100.com:80";
	private static final String DEFAULT_API_SERVER = "http://localhost:8000";
	private static String SEARCH_GROUP_BUY_WEB_CONTEXT = "/api/i?";

	public static InputStream getResponse(String requestURL)
			throws MalformedURLException, UnsupportedEncodingException,
			IOException, ProtocolException {
		URL serverAddress = new URL(requestURL);
		HttpURLConnection connection = (HttpURLConnection) serverAddress
				.openConnection();
		connection.setRequestMethod("GET");
//		connection.setRequestProperty("Accept-Encoding","gzip, deflate");  
		connection.connect();

		InputStream inputStream = connection.getInputStream();
		return inputStream;
	}

	public static String getRequestURL(HttpServletRequest req)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer(getSearchGroupBuyUrl());
		@SuppressWarnings("rawtypes")
		Enumeration e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String name = String.valueOf(e.nextElement());

			String[] values = req.getParameterValues(name);
			for (String v : values) {
				String value = URLEncoder.encode(v, "UTF-8");
				sb.append("&").append(name).append("=").append(value);
			}
		}
		return sb.toString();
	}

	public static String getSearchGroupBuyUrl() {
		String server = DEFAULT_API_SERVER;

		if (System.getProperty(PROPERTY_API_SERVER) != null) {
			server = System.getProperty(PROPERTY_API_SERVER);
		}
		return server + SEARCH_GROUP_BUY_WEB_CONTEXT;
	}
}
