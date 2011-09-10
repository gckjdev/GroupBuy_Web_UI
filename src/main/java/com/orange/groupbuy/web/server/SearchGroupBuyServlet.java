package com.orange.groupbuy.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchGroupBuyServlet extends HttpServlet {

	private static final String PROPERTY_API_SERVER = "groupbuy.api.server";
	private static final String DEFAULT_API_SERVER = "http://localhost:8000";
	private static String SEARCH_GROUP_BUY_WEB_CONTEXT = "/api/i?";
	/**
	 * 
	 */
	private static final long serialVersionUID = 5582162516572852098L;

	private Logger log = Logger
			.getLogger(SearchGroupBuyServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		try {
			URL serverAddress = new URL(getRequestURL(req));
			HttpURLConnection connection = (HttpURLConnection) serverAddress
					.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			InputStream inputStream = connection.getInputStream();
			copyLarge(inputStream, resp.getOutputStream());
			inputStream.close();
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
	}

	public static long copyLarge(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[4096];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	private String getRequestURL(HttpServletRequest req)
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

		// System.out.println(sb.toString());
		return sb.toString();
	}

	private String getSearchGroupBuyUrl() {
		String server = DEFAULT_API_SERVER;

		if (System.getProperty(PROPERTY_API_SERVER) != null) {
			server = System.getProperty(PROPERTY_API_SERVER);
		}
		return server + SEARCH_GROUP_BUY_WEB_CONTEXT;
	}
}
