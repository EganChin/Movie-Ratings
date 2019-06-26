package com.mr.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author LiangYongjie
 * @date 2019-01-05
 */
public class DateUtils {

	private static ThreadLocal<Calendar> calendars = ThreadLocal.withInitial(Calendar::getInstance);

    public final static String DATE = "yyyy-MM-dd";
    public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE);
    }

    public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
    }

    public static String format(long timestamp) {
        return format(new Date(timestamp));
    }

    public static String format(long timestamp, String pattern) {
        return format(new Date(timestamp), pattern);
    }


    public static Long getMonthBegin(Long timestamp){
		return getMonthBE(timestamp,
			1,
			0,
			0,
			0,
			0);
    }

    public static Long getMonthEnd(Long timestamp){
		Calendar c = calendars.get();
        return getMonthBE(timestamp,
			c.getActualMaximum(Calendar.DAY_OF_MONTH),
			23,
			59,
			59,
			999);
    }

	public static Long getDayBegin(Long timestamp) {
		return getDayBE(timestamp, 0, 0, 0, 0);
	}

	public static Long getDayEnd(Long timestamp) {
		return getDayBE(timestamp, 23, 59, 59, 999);
	}

	private static Long getDayBE(Long timestamp,
								   Integer hour,
								   Integer minute,
								   Integer second,
								   Integer millisecond){
		Calendar c = calendars.get();
		c.setTimeInMillis(timestamp);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, millisecond);
		return c.getTimeInMillis();
	}

    private static Long getMonthBE(Long timestamp,
								   Integer day,
								   Integer hour,
								   Integer minute,
								   Integer second,
								   Integer millisecond){
    	Calendar c = calendars.get();
		c.setTimeInMillis(timestamp);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, millisecond);
		return c.getTimeInMillis();
	}

}
