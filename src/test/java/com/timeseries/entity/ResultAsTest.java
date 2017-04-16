package com.timeseries.entity;

import org.junit.Test;

import com.timeseries.exception.InvalidDataPoint;

public class ResultAsTest {
	
	public static final Integer[] ALL_WEEK_LONG_AS_BUSINESS_DAYS = new Integer[]{ 1, 2, 3, 4, 5, 6, 7 };

	@Test
	public void betterCaseMean() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Mean();
		result.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,07-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,09-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		
		System.out.println( result.sum() );
	}
	
	@Test
	public void betterCaseMeanWithLimit() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Mean( new Limit( 7 ) );
		result.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,07-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,09-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		
		System.out.println( result.sum() );
	}
	
	@Test
	public void betterCaseSum() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Sum();
		result.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,07-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,09-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		
		System.out.println( result.sum() );
	}
	
	@Test
	public void betterCaseSumWithLimit() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Sum( new Limit( 7 ) );
		result.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,07-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,09-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		
		System.out.println( result.sum() );
	}
	
}
