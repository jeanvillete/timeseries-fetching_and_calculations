package com.timeseries.test;

import java.time.format.DateTimeFormatter;

import com.timeseries.entity.DataPoint;

public interface CommonStatics {
	
	public static final Integer[] ALL_WEEK_LONG_AS_BUSINESS_DAYS = new Integer[]{ 1, 2, 3, 4, 5, 6, 7 };
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern( DataPoint.LOCAL_DATE_PATTERN );

}
