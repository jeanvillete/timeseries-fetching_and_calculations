package com.timeseries.test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Test;

import com.timeseries.TimeSeriesProcessor;
import com.timeseries.entity.DataPoint;
import com.timeseries.entity.DateRange;
import com.timeseries.entity.DaysOfWeekRange;
import com.timeseries.entity.Limit;
import com.timeseries.entity.Query;
import com.timeseries.entity.ResultAs;
import com.timeseries.entity.enums.Ordering;

public class TimeSeriesProcessorTest {
	
	@Test
	public void betterCase() {
		new TimeSeriesProcessor(
			// any other instrument from the input file - sum of the newest 10 instrument values (in terms of the date).
			( instrument ) -> new Query( instrument, new ResultAs.Sum( new Limit( 10, Ordering.NEWEST ) ) ),
			
			// INSTRUMENT1 – mean of all the values
			new Query( "INSTRUMENT1", new ResultAs.Mean() ),
			
			// INSTRUMENT2 – mean of all the values from November 2014
			new Query( "INSTRUMENT2", new ResultAs.Mean(), DateRange.from( LocalDate.parse( "01-Jan-2014", DataPoint.FORMATTER ) ) ),
			
			// INSTRUMENT3 – any other statistical calculation that we can compute "on-the-fly" as we read the file (it's up to you)
			new Query( "INSTRUMENT3", new ResultAs.Sum( new Limit( 25, Ordering.OLDEST ) ), new DaysOfWeekRange( DayOfWeek.FRIDAY ) ), // sum of the oldest 25 fridays 
			new Query( "INSTRUMENT3", new ResultAs.Mean( new Limit( 10, Ordering.NEWEST ) ), new DaysOfWeekRange( DayOfWeek.MONDAY ) ) // mean of the newest 25 mondays
		)
		.process( "/home/jean/documents/natek/24004_test/example_input.txt" )
		.print();
	}

}