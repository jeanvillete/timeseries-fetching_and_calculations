package com.timeseries.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.util.StringUtils;

import com.timeseries.exception.InvalidDataPoint;

public class DataPoint {
	
	public static final String LOCAL_DATE_PATTERN = "dd-MMM-yyyy";

	private String instrument;
	private LocalDate date;
	private double value;
	private float multiplier = 1.0F;
	
	public DataPoint( String line ) throws InvalidDataPoint {
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
		try {
			this.value = Double.parseDouble( parsing[ 2 ] );
		} catch ( NumberFormatException e ) {
			throw new InvalidDataPoint( e );
		}
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
	
	void setMultiplier( float multiplier ) {
		this.multiplier = multiplier;
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
	public float getMultiplier() {
		return multiplier;
	}
}
