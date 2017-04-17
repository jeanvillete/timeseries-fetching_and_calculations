package com.timeseries.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import org.springframework.util.StringUtils;

import com.timeseries.exception.InvalidDataPoint;

public class DataPoint {
	
	public static final String LOCAL_DATE_PATTERN = "dd-MMM-yyyy";
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern( LOCAL_DATE_PATTERN );

	private String instrument;
	private LocalDate date;
	private double value;
	private double multiplier = 1.0;
	
	public DataPoint( String line, Integer... businessDays ) throws InvalidDataPoint {
		super();
		
		String[] parsing;
		if ( !StringUtils.hasText( line ) || ( parsing = line.split( "," ) ) == null || parsing.length != 3 )
			throw new InvalidDataPoint( "It was not possible parse the content; line=[" + line + "]" );
		
		this.instrument = parsing[ 0 ];
		
		try {
			this.date = LocalDate.parse( parsing[ 1 ], DateTimeFormatter.ofPattern( LOCAL_DATE_PATTERN ) );
		} catch ( DateTimeParseException e ) {
			throw new InvalidDataPoint( e );
		}
		
		if ( !Arrays.asList( businessDays ).contains( this.date.getDayOfWeek().getValue() ) ) {
			throw new InvalidDataPoint( "This is not a business date." );
		}
		
		try {
			this.value = Double.parseDouble( parsing[ 2 ] );
		} catch ( NumberFormatException e ) {
			throw new InvalidDataPoint( e );
		}
	}
	
	/**
	 * Default business days as the range from Monday to Friday (Saturday and Sunday are not included)
	 * @param line
	 * @throws InvalidDataPoint
	 */
	public DataPoint( String line ) throws InvalidDataPoint {
		this( line, 1, 2, 3, 4, 5 );
	}
	
	/**
	 * DataPoint instances are equal if their String 'instrument' and LocalDate 'date' properties are equal.
	 */
	@Override
	public boolean equals( Object other ) {
		if ( !( other instanceof DataPoint ) ) return false;
		DataPoint _other = ( DataPoint ) other;
		return this.instrument.equals( _other.instrument ) && this.date.isEqual( _other.date );
	}
	
	
	public String getInstrument() {
		return instrument;
	}
	public LocalDate getDate() {
		return date;
	}
	public double getValue() {
		return value;
	}
	public void setMultiplier( double multiplier ) {
		this.multiplier = multiplier;
	}
	public double getMultiplier() {
		return multiplier;
	}
}
