package com.people2000.common.cache;

public class CacheConstants {
	public static final int DEFAULT_EXPIRE_TIME = 20;
	public static final int EXPIRE_30MIN = 30;
	public static final int EXPIRE_1H = 60;
	public static final int EXPIRE_2H = 120;
	public static final int EXPIRE_6H = 360;
	public static final int EXPIRE_12H = 720;
	public static final int EXPIRE_24H = 1440;
	public static final int EXPIRE_48H = 2880;
	public static final String DELETE_FAIL_KEY = "deleteFailTimes";
	public static final String STAT_FAIL_KEY = "statFailTimes";
	public static final String PUT_FAIL_KEY = "putFailTimes";
	public static final String INCR_FAIL_KEY = "incrfailTimes";
	public static final String DELETE_FAIL_DATE = "deleteFailDate";
	public static final String STAT_FAIL_DATE = "statFailDate";
	public static final String PUT_FAIL_DATE = "putFailDate";
	public static final String INCR_FAIL_DATE = "incrfailDate";
	public static final int EXPIRE_MILLISECOND_60s = 60000;
}
