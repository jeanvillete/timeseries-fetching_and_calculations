package com.timeseries.entity.test;

import static com.timeseries.test.CommonStatics.ALL_WEEK_LONG_AS_BUSINESS_DAYS;
import static com.timeseries.entity.DataPoint.FORMATTER;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.util.Assert;

import com.timeseries.entity.DataPoint;
import com.timeseries.entity.DateRange;
import com.timeseries.entity.Range;
import com.timeseries.exception.InvalidDataPoint;

public class DateRangeTest {

	@Test
	public void betterCaseFromAndTo() throws InvalidDataPoint {
		Range range = new DateRange( LocalDate.parse( "01-Jan-2000", FORMATTER ), LocalDate.parse( "31-Jan-2000", FORMATTER ) );
		
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,31-Dec-1999,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,07-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,09-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,10-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,13-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,15-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,31-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Feb-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,14-Apr-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
	}
	
	@Test
	public void betterCaseFrom() throws InvalidDataPoint {
		Range range = DateRange.from( LocalDate.parse( "01-Jan-2000", FORMATTER ) );
		
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,31-Dec-1999,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,07-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,09-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,10-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,13-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,15-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,31-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,01-Feb-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Apr-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
	}
	
	@Test
	public void betterCaseTo() throws InvalidDataPoint {
		Range range = DateRange.to( LocalDate.parse( "31-Jan-2000", FORMATTER ) );
		
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,31-Dec-1999,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,07-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,09-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,10-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,13-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,15-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,31-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Feb-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,14-Apr-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) );
	}
	
}
