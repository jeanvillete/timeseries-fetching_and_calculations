package com.timeseries.entity.test;

import static com.timeseries.test.CommonStatics.ALL_WEEK_LONG_AS_BUSINESS_DAYS;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.springframework.util.Assert;

import com.timeseries.entity.DataPoint;
import com.timeseries.entity.Limit;
import com.timeseries.entity.ResultAs;
import com.timeseries.entity.enums.Ordering;
import com.timeseries.exception.InvalidDataPoint;

public class ResultAsTest {
	
	private void fillResult( ResultAs result ) throws InvalidDataPoint {
		result.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,02-Jan-1996,2.2", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,03-Jan-1996,3.33", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,04-Jan-1996,4.4444", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,05-Jan-1996,5.55555", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,07-Jan-1996,6.666666", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,09-Jan-1996,7.7777777", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,10-Jan-1996,8.88888888", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,13-Jan-1996,9.999999999", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Jan-1996,10", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,15-Jan-1996,11.1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,01-Feb-1996,12.22", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
		result.add( new DataPoint( "INSTRUMENT,14-Apr-1996,13.333", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) );
	}

	@Test
	public void betterCaseMean_1() throws InvalidDataPoint {
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
		
		Assert.isTrue( result.sum() == 1.0 );
	}
	
	@Test
	public void betterCaseMean_2() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Mean();
		this.fillResult( result );
		
		double expected = 	7.4243294292;
		BigDecimal sum = new BigDecimal( result.sum() ).setScale( 10, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
	}
	
	@Test
	public void betterCaseMeanWithLimitNewest() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Mean( new Limit( 7, Ordering.NEWEST ) );
		this.fillResult( result );
		
		double expected = 10.4742380827;
		BigDecimal sum = new BigDecimal( result.sum() ).setScale( 10, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
	}
	
	@Test
	public void betterCaseMeanWithLimitOldest() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Mean( new Limit( 7, Ordering.OLDEST ) );
		this.fillResult( result );
		
		double expected = 4.4249133857;
		BigDecimal sum = new BigDecimal( result.sum() ).setScale( 10, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
	}
	
	@Test
	public void betterCaseSum_1() throws InvalidDataPoint {
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
		
		Assert.isTrue( result.sum() == 13.0 );
	}
	
	@Test
	public void betterCaseSum_2() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Sum();
		this.fillResult( result );
		
		double expected = 96.516282579;
		BigDecimal sum = new BigDecimal( result.sum() ).setScale( 9, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
	}
	
	@Test
	public void betterCaseSumWithLimitNewest() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Sum( new Limit( 7, Ordering.NEWEST ) );
		this.fillResult( result );

		double expected = 73.319666579;
		BigDecimal sum = new BigDecimal( result.sum() ).setScale( 9, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
	}

	@Test
	public void betterCaseSumWithLimitOldest() throws InvalidDataPoint {
		ResultAs result = new ResultAs.Sum( new Limit( 7, Ordering.OLDEST ) );
		this.fillResult( result );

		double expected = 30.9743937;
		BigDecimal sum = new BigDecimal( result.sum() ).setScale( 9, RoundingMode.HALF_UP );
		Assert.isTrue( sum.doubleValue() == expected );
	}
	
}
