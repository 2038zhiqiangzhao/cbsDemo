package com.people2000.user.business.utils;

public class PageUtils {

	public static int getStartItem(Integer currentPage, Integer itemsPerPage) {
		int start = (currentPage - 1) * itemsPerPage;
		if (start < 0) {
			start = 0;
		}
		return start;
	}
}
