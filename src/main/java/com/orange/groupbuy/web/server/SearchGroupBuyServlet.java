package com.orange.groupbuy.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.groupbuy.web.server.util.ProxyUtil;

public class SearchGroupBuyServlet extends HttpServlet {

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
			String requestURL = ProxyUtil.getRequestURL(req);
			InputStream inputStream = ProxyUtil.getResponse(requestURL);
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
}
