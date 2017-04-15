package com.timeseries.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.springframework.util.Assert;

import com.timeseries.entity.enums.Ordering;
import com.timeseries.exception.InvalidDataPoint;

public class LimitTest {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern( DataPoint.LOCAL_DATE_PATTERN );

	@Test
	public void betterCaseForOldest() throws InvalidDataPoint {
		Limit limit = new Limit( 7, Ordering.OLDEST );
		limit.add( new DataPoint( "INSTRUMENT,07-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1" ) );
		
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );
		
		limit.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1" ) );
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );

		limit.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1" ) );
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );

		limit.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,09-Jan-1996,1" ) );
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );
		
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "09-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "07-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "05-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "04-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "03-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "02-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "01-Jan-1996", FORMATTER ) ) );
	}
	
	@Test
	public void betterCaseForNewest() throws InvalidDataPoint {
		Limit limit = new Limit( 7 );
		limit.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,15-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,13-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,03-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,02-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,04-Jan-1996,1" ) );
		
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );
		
		limit.add( new DataPoint( "INSTRUMENT,01-Jan-1996,1" ) );
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );

		limit.add( new DataPoint( "INSTRUMENT,01-Feb-1996,1" ) );
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );

		limit.add( new DataPoint( "INSTRUMENT,10-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,14-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,05-Jan-1996,1" ) );
		limit.add( new DataPoint( "INSTRUMENT,14-Apr-1996,1" ) );
		Assert.isTrue( limit.size() == 7, "It was expected have 7 elements at this point." );
		
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "05-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "10-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "13-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "14-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "15-Jan-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "01-Feb-1996", FORMATTER ) ) );
		Assert.isTrue( limit.poll().getDate().isEqual( LocalDate.parse( "14-Apr-1996", FORMATTER ) ) );
	}
	
}
