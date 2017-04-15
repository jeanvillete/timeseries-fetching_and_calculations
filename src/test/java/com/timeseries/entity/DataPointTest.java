package com.timeseries.entity;

import java.time.format.DateTimeParseException;

import org.junit.Test;
import org.springframework.util.Assert;

import com.timeseries.exception.InvalidDataPoint;

public class DataPointTest {

	@Test
	public void betterCase() throws InvalidDataPoint {
		String pattern;
		DataPoint dataPoint;
		
		Assert.notNull( dataPoint = new DataPoint( pattern = "INSTRUMENT1,01-Jan-1996,2.4655" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		Assert.isTrue( dataPoint.getInstrument().equals( "INSTRUMENT1" ), "Invalid parsed instrument=[" + dataPoint.getInstrument() + "]" );
		Assert.isTrue( dataPoint.getValue() == 2.4655, "Invalid parsed value=[" + dataPoint.getValue() + "]" );
		Assert.isTrue( dataPoint.getDate().getDayOfMonth() == 1 && dataPoint.getDate().getMonthValue() == 1 && dataPoint.getDate().getYear() == 1996, "Invalid parsed date=[" + dataPoint.getDate() + "]" );
		
		Assert.notNull( dataPoint = new DataPoint( pattern = "INSTRUMENT2,07-Jan-2005,9.319435282" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		Assert.isTrue( dataPoint.getInstrument().equals( "INSTRUMENT2" ), "Invalid parsed instrument=[" + dataPoint.getInstrument() + "]" );
		Assert.isTrue( dataPoint.getValue() == 9.319435282, "Invalid parsed value=[" + dataPoint.getValue() + "]" );
		Assert.isTrue( dataPoint.getDate().getDayOfMonth() == 7 && dataPoint.getDate().getMonthValue() == 1 && dataPoint.getDate().getYear() == 2005, "Invalid parsed date=[" + dataPoint.getDate() + "]" );
		
		Assert.notNull( dataPoint = new DataPoint( pattern = "INSTRUMENT3,09-Apr-2008,102.37" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		Assert.isTrue( dataPoint.getInstrument().equals( "INSTRUMENT3" ), "Invalid parsed instrument=[" + dataPoint.getInstrument() + "]" );
		Assert.isTrue( dataPoint.getValue() == 102.37, "Invalid parsed value=[" + dataPoint.getValue() + "]" );
		Assert.isTrue( dataPoint.getDate().getDayOfMonth() == 9 && dataPoint.getDate().getMonthValue() == 4 && dataPoint.getDate().getYear() == 2008, "Invalid parsed date=[" + dataPoint.getDate() + "]" );
	}
	
	@Test( expected = NumberFormatException.class )
	public void invalidParsingValue() throws Throwable {
		String pattern;
		try {
			Assert.notNull( new DataPoint( pattern = "INSTRUMENT1,01-Jan-1996,NAN" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		} catch ( InvalidDataPoint e ) {
			throw e.getCause();
		}
	}
	
	@Test( expected = DateTimeParseException.class )
	public void invalidParsingDate_1() throws Throwable {
		String pattern;
		try {
			Assert.notNull( new DataPoint( pattern = "INSTRUMENT1,01-01-1996,2.4655" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		} catch ( InvalidDataPoint e ) {
			throw e.getCause();
		}
	}
	
	@Test( expected = DateTimeParseException.class )
	public void invalidParsingDate_2() throws Throwable {
		String pattern;
		try {
			Assert.notNull( new DataPoint( pattern = "INSTRUMENT1,1-Jan-1996,2.4655" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		} catch ( InvalidDataPoint e ) {
			throw e.getCause();
		}
	}
	
	@Test( expected = DateTimeParseException.class )
	public void invalidParsingDate_3() throws Throwable {
		String pattern;
		try {
			Assert.notNull( new DataPoint( pattern = "INSTRUMENT1,01-Jan-96,2.4655" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		} catch ( InvalidDataPoint e ) {
			throw e.getCause();
		}
	}
	
	@Test( expected = DateTimeParseException.class )
	public void invalidParsingDate_4() throws Throwable  {
		String pattern;
		try {
			Assert.notNull( new DataPoint( pattern = "INSTRUMENT1,01/Jan/1996,2.4655" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
		} catch ( InvalidDataPoint e ) {
			throw e.getCause();
		}
	}
	
	@Test( expected = InvalidDataPoint.class )
	public void invalidLineContent_1() throws InvalidDataPoint {
		String pattern;
		Assert.notNull( new DataPoint( pattern = "" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
	}
	
	@Test( expected = InvalidDataPoint.class )
	public void invalidLineContent_2() throws InvalidDataPoint {
		String pattern;
		Assert.notNull( new DataPoint( pattern = "INSTRUMENT1" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
	}
	
	@Test( expected = InvalidDataPoint.class )
	public void invalidLineContent_3() throws InvalidDataPoint {
		String pattern;
		Assert.notNull( new DataPoint( pattern = "INSTRUMENT1,01-Jan-1996" ), "Impossible instantiate expected object. based on pattern=[" + pattern + "]" );
	}
	
	@Test
	public void checkEquality() throws InvalidDataPoint {
		Assert.isTrue( new DataPoint( "INSTRUMENT1,01-Jan-1996,123" ).equals( new DataPoint( "INSTRUMENT1,01-Jan-1996,321" ) ),
				"The provided value for 'instrument' and 'date' were the same for both objects, only the double 'value' was different, so they have to be considered equal." );
	}
	
}
