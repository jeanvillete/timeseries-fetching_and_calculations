package com.timeseries.entity;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class DaysOfWeekRange implements Range {
	
	private List< DayOfWeek > daysOfWeek;
	
	public DaysOfWeekRange( DayOfWeek... daysOfWeek ) {
		if ( daysOfWeek == null || daysOfWeek.length < 1 )
			throw new IllegalArgumentException( "It has to be provided at least on day of week." );
		
		this.daysOfWeek = Arrays.asList( daysOfWeek );
	}

	@Override
	public boolean isItInRange( DataPoint dataPoint ) {
		return this.daysOfWeek.contains( dataPoint.getDate().getDayOfWeek() );
	}

}
