package com.people2000.user.business.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by xiaole on 2016/2/19.
 */
public class TimeUtils {

	private static final Long MinuteInMs = 60 * 1000L;

	public static int getMinFromNowTOToday() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long diff = (cal.getTimeInMillis() - System.currentTimeMillis())
				/ MinuteInMs;
		return (int) diff;
	}
}
