package com.orange.groupbuy.web.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateUtil {

	public static String DATE_FORMAT = "yyyyMMddHHmmss";
	public static String CHINA_TIMEZONE = "GMT+0800";

	public static String formatReadableDate(String dateToParse) {
		if (dateToParse == null || dateToParse.isEmpty()) {
			return "";
		}
		DateTimeFormat parser = DateTimeFormat.getFormat(DATE_FORMAT);
		DateTimeFormat formatter = DateTimeFormat.getFormat("yyyy年MM月dd日");
		Date date = parser.parse(dateToParse);
		String result = formatter.format(date);
		return result;
	}

	public static int calcHour(Date startDate, Date endDate) {
		if (startDate.before(endDate)) {
			long start = startDate.getTime();
			long end = endDate.getTime();
			int hours = (int) ((end - start) / (3600L * 1000));
			return hours;
		} else {
			return -1;
		}
	}
}
