package com.timeseries.entity;

import java.time.LocalDate;

public class DateRange implements Range {

	private LocalDate from, to;
	
	public DateRange( LocalDate from, LocalDate to ) {
		if ( from == null && to == null ) {
			throw new IllegalArgumentException( "At least one argument has to be provided, either 'from' or 'to'." );
		}
		
		this.from = from;
		this.to = to;
	}
	
	public static DateRange from( LocalDate from ) {
		return new DateRange( from, null );
	}
	
	public static DateRange to( LocalDate to ) {
		return new DateRange( null, to );
	}
	
	@Override
	public boolean isItInRange( DataPoint dataPoint ) {
		boolean from = true; 
		if ( this.from != null )
			from = this.from.isEqual( dataPoint.getDate() ) || this.from.isAfter( dataPoint.getDate() );
		
		boolean to = true;
		if ( this.to != null )
			to = this.to.isEqual( dataPoint.getDate() ) || this.to.isAfter( dataPoint.getDate() );
		
		return from && to;
	}

}