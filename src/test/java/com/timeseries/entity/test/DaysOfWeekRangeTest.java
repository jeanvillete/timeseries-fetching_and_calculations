package com.timeseries.entity.test;

import static com.timeseries.test.CommonStatics.ALL_WEEK_LONG_AS_BUSINESS_DAYS;

import java.time.DayOfWeek;

import org.junit.Test;
import org.springframework.util.Assert;

import com.timeseries.entity.DataPoint;
import com.timeseries.entity.DaysOfWeekRange;
import com.timeseries.entity.Range;
import com.timeseries.exception.InvalidDataPoint;

public class DaysOfWeekRangeTest {
	
	@Test( expected = IllegalArgumentException.class )
	public void exceptionNoneDayOfWeekProvided() throws InvalidDataPoint {
		new DaysOfWeekRange();
	}
	
	@Test
	public void betterCaseMONDAY_WEDNESDAY_FRIDAY() throws InvalidDataPoint {
		Range range = new DaysOfWeekRange( DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY );
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 01-Jan-1996 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 02-Jan-1996 <-> TUESDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 03-Jan-1996 <-> WEDNESDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 04-Jan-1996 <-> THURSDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 05-Jan-1996 <-> FRIDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,31-Dec-1999,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 31-Dec-1999 <-> FRIDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 01-Jan-2000 <-> SATURDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 02-Jan-2000 <-> SUNDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 03-Jan-2000 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 04-Jan-2000 <-> TUESDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 05-Jan-2000 <-> WEDNESDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,07-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 07-Jan-2000 <-> FRIDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,09-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 09-Jan-2000 <-> SUNDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,10-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 10-Jan-2000 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,13-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 13-Jan-2000 <-> THURSDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 14-Jan-2000 <-> FRIDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,15-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 15-Jan-2000 <-> SATURDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,31-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 31-Jan-2000 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Feb-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 01-Feb-2000 <-> TUESDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Apr-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 14-Apr-2000 <-> FRIDAY
	}

	@Test
	public void betterCaseOnlyFRIDAY() throws InvalidDataPoint {
		Range range = new DaysOfWeekRange( DayOfWeek.FRIDAY );
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 01-Jan-1996 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 02-Jan-1996 <-> TUESDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 03-Jan-1996 <-> WEDNESDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 04-Jan-1996 <-> THURSDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-1996,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 05-Jan-1996 <-> FRIDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,31-Dec-1999,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 31-Dec-1999 <-> FRIDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 01-Jan-2000 <-> SATURDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,02-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 02-Jan-2000 <-> SUNDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,03-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 03-Jan-2000 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,04-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 04-Jan-2000 <-> TUESDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,05-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 05-Jan-2000 <-> WEDNESDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,07-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 07-Jan-2000 <-> FRIDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,09-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 09-Jan-2000 <-> SUNDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,10-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 10-Jan-2000 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,13-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 13-Jan-2000 <-> THURSDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 14-Jan-2000 <-> FRIDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,15-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 15-Jan-2000 <-> SATURDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,31-Jan-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 31-Jan-2000 <-> MONDAY
		Assert.isTrue( !range.isItInRange( new DataPoint( "INSTRUMENT,01-Feb-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 01-Feb-2000 <-> TUESDAY
		Assert.isTrue( range.isItInRange( new DataPoint( "INSTRUMENT,14-Apr-2000,1", ALL_WEEK_LONG_AS_BUSINESS_DAYS ) ) ); // 14-Apr-2000 <-> FRIDAY
	}
	
}
