package com.timeseries.entity;

import org.springframework.util.StringUtils;

public class Query {

	private String instrument;
	private ResultAs resultAs = new ResultAs.Sum();
	private Range range;
	
	/**
	 * As default a 'resultAs' is defined as ResultAs.Sum (with no Limit defined)
	 * 
	 * @param instrument
	 */
	public Query( String instrument ) {
		super();
		
		if ( !StringUtils.hasText( instrument ) )
			throw new IllegalArgumentException( "The argument 'instrument' is mandatory." );
		
		this.instrument = instrument;
	}
	
	public Query( String instrument, ResultAs resultAs ) {
		this( instrument );
		
		if ( resultAs == null )
			throw new IllegalArgumentException( "Argument 'resultAs' is mandatory." );
		
		this.resultAs = resultAs;
	}
	
	/**
	 * As default a 'resultAs' is defined as ResultAs.Sum (with no Limit defined)
	 * 
	 * @param instrument
	 */
	public Query( String instrument, Range range ) {
		this( instrument );
		
		if ( range == null )
			throw new IllegalArgumentException( "Argument 'range' is mandatory." );
		
		this.range = range;
	}
	
	public Query( String instrument, ResultAs resultAs, Range range ) {
		this( instrument );
		
		if ( resultAs == null )
			throw new IllegalArgumentException( "Argument 'resultAs' is mandatory." );
		if ( range == null )
			throw new IllegalArgumentException( "Argument 'range' is mandatory." );
		
		this.resultAs = resultAs;
		this.range = range;
	}
	
	public void add( DataPoint dataPoint ) {
		if ( this.range == null || this.range.isItInRange( dataPoint ) )
			this.resultAs.add( dataPoint );
	}

	public String getInstrument() {
		return instrument;
	}
	
	public double sum() {
		return this.resultAs.sum();
	}
	
	@Override
	public String toString() {
		return "Query result, Instrument = [" + this.instrument + "]" +
				( this.range != null ? this.range.toString() : "" ) +
				this.resultAs.toString() + "=[" + this.resultAs.sum() + "]";
	}
}
