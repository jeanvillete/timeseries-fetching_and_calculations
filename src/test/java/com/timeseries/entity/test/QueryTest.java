package com.timeseries.entity.test;

import static com.timeseries.test.CommonStatics.ALL_WEEK_LONG_AS_BUSINESS_DAYS;
import static com.timeseries.test.CommonStatics.FORMATTER;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Test;
import org.springframework.util.Assert;

import com.timeseries.entity.DataPoint;
import com.timeseries.entity.DateRange;
import com.timeseries.entity.DaysOfWeekRange;
import com.timeseries.entity.Limit;
import com.timeseries.entity.Query;
import com.timeseries.entity.ResultAs;
import com.timeseries.entity.enums.Ordering;
import com.timeseries.exception.InvalidDataPoint;

public class QueryTest {
	
	private void fillQuery( Query query ) throws InvalidDataPoint {
		query.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,02-Jan-1996,2.2", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,03-Jan-1996,3.33", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,04-Jan-1996,4.4444", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,05-Jan-1996,5.55555", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,07-Jan-1996,6.666666", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,09-Jan-1996,7.7777777", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,10-Jan-1996,8.88888888", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,13-Jan-1996,9.999999999", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,14-Jan-1996,10", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,15-Jan-1996,11.1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,01-Feb-2000,12.22", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,14-Apr-2017,13.333", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
	}

	@Test
	public void betterCaseMean_1() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Mean() );
		query.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,07-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,09-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		
		Assert.isTrue( query.sum() == 1.0 );
		
		System.out.println( "\nQueryTest.betterCaseMean_1" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseMean_2() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Mean() );
		this.fillQuery( query );
		
		double expected = 	7.4243294292;
		BigDecimal sum = new BigDecimal( query.sum() ).setScale( 10, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
		
		System.out.println( "\nQueryTest.betterCaseMean_2" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseMeanWithLimitNewest() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Mean( new Limit( 7, Ordering.NEWEST ) ) );
		this.fillQuery( query );
		
		double expected = 10.4742380827;
		BigDecimal sum = new BigDecimal( query.sum() ).setScale( 10, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
		
		System.out.println( "\nQueryTest.betterCaseMeanWithLimitNewest" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseMeanWithLimitOldest() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Mean( new Limit( 7, Ordering.OLDEST ) ) );
		this.fillQuery( query );
		
		double expected = 4.4249133857;
		BigDecimal sum = new BigDecimal( query.sum() ).setScale( 10, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
		
		System.out.println( "\nQueryTest.betterCaseMeanWithLimitOldest" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseSum_1() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Sum() );
		query.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,07-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,09-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		query.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		
		Assert.isTrue( query.sum() == 13.0 );
		
		System.out.println( "\nQueryTest.betterCaseSum_1" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseSum_2() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Sum() );
		this.fillQuery( query );
		
		double expected = 96.516282579;
		BigDecimal sum = new BigDecimal( query.sum() ).setScale( 9, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
		
		System.out.println( "\nQueryTest.betterCaseSum_2" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseSumWithLimitNewest() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Sum( new Limit( 7, Ordering.NEWEST ) ) );
		this.fillQuery( query );

		double expected = 73.319666579;
		BigDecimal sum = new BigDecimal( query.sum() ).setScale( 9, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
		
		System.out.println( "\nQueryTest.betterCaseSumWithLimitNewest" );
		System.out.println( query );
	}

	@Test
	public void betterCaseSumWithLimitOldest() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Sum( new Limit( 7, Ordering.OLDEST ) ) );
		this.fillQuery( query );

		double expected = 30.9743937;
		BigDecimal sum = new BigDecimal( query.sum() ).setScale( 9, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
		
		System.out.println( "\nQueryTest.betterCaseSumWithLimitOldest" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseSumWithLimitOldestWithDateRange() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new ResultAs.Sum( new Limit( 7, Ordering.OLDEST ) ), new DateRange( LocalDate.parse( "01-Jan-2000", FORMATTER ), LocalDate.parse( "31-Jan-2000", FORMATTER ) ) );
		this.fillQuery( query );
		
		double expected = 0.0;
		Assert.isTrue( query.sum() == expected );
		
		System.out.println( "\nQueryTest.betterCaseSumWithLimitOldestWithDateRange" );
		System.out.println( query );
	}
	
	@Test
	public void betterCaseSumWithLimitOldestWithDaysOfWeek() throws InvalidDataPoint {
		Query query = new Query( "INSTRUMENT", new DaysOfWeekRange( DayOfWeek.MONDAY, DayOfWeek.FRIDAY ) );
		
		query.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 01-Jan-1996 <-> MONDAY
		query.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 02-Jan-1996 <-> TUESDAY
		query.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 03-Jan-1996 <-> WEDNESDAY
		query.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 04-Jan-1996 <-> THURSDAY
		query.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 05-Jan-1996 <-> FRIDAY
		query.add( new DataPoint( "INSTRUMENT,31-Dec-1999,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 31-Dec-1999 <-> FRIDAY
		query.add( new DataPoint( "INSTRUMENT,01-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 01-Jan-2000 <-> SATURDAY
		query.add( new DataPoint( "INSTRUMENT,02-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 02-Jan-2000 <-> SUNDAY
		query.add( new DataPoint( "INSTRUMENT,03-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 03-Jan-2000 <-> MONDAY
		query.add( new DataPoint( "INSTRUMENT,04-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 04-Jan-2000 <-> TUESDAY
		query.add( new DataPoint( "INSTRUMENT,05-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 05-Jan-2000 <-> WEDNESDAY
		query.add( new DataPoint( "INSTRUMENT,07-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 07-Jan-2000 <-> FRIDAY
		query.add( new DataPoint( "INSTRUMENT,09-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 09-Jan-2000 <-> SUNDAY
		query.add( new DataPoint( "INSTRUMENT,10-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 10-Jan-2000 <-> MONDAY
		query.add( new DataPoint( "INSTRUMENT,13-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 13-Jan-2000 <-> THURSDAY
		query.add( new DataPoint( "INSTRUMENT,14-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 14-Jan-2000 <-> FRIDAY
		query.add( new DataPoint( "INSTRUMENT,15-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 15-Jan-2000 <-> SATURDAY
		query.add( new DataPoint( "INSTRUMENT,31-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 31-Jan-2000 <-> MONDAY
		query.add( new DataPoint( "INSTRUMENT,01-Feb-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 01-Feb-2000 <-> TUESDAY
		query.add( new DataPoint( "INSTRUMENT,14-Apr-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ); // 14-Apr-2000 <-> FRIDAY
		
		double expected = 9.0;
		Assert.isTrue( query.sum() == expected );
		
		System.out.println( "\nQueryTest.betterCaseSumWithLimitOldestWithDaysOfWeek" );
		System.out.println( query );
	}
	
}
