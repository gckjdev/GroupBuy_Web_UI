package com.orange.groupbuy.web.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class StringUtil {
    public static long copyLarge(InputStream input, StringWriter output)
            throws IOException {
        char[] buffer = new char[4096];
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        long count = 0;
        int n = 0;
        while (-1 != (n = reader.read(buffer))) {
            output.write(new String(buffer));
            count += n;
        }
        return count;
    }

}
