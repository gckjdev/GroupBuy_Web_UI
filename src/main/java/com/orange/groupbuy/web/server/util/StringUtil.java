package com.orange.groupbuy.web.server.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class StringUtil {
	public static long copyLarge(InputStream input, StringWriter output)
			throws IOException {
		byte[] buffer = new byte[4096];
		BufferedInputStream in = new BufferedInputStream(input);
		long count = 0;
		int n = 0;
		while (-1 != (n = in.read(buffer))) {
			output.write(new String(buffer, "utf-8"));
			count += n;
		}
		return count;
	}
}
