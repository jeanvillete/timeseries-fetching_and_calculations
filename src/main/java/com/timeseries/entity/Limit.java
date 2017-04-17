package com.timeseries.entity;

import java.util.PriorityQueue;

import com.timeseries.entity.enums.Ordering;

public class Limit {
	
	private int max;
	private Ordering ordering;
	private PriorityQueue< DataPoint > data;
	
	/**
	 * In this case the default ordering is Ordering.NEWEST
	 * @param max
	 */
	public Limit( int max ) {
		this( max, Ordering.NEWEST );
	}
	
	/**
	 * 
	 * @param max
	 * @param ordering
	 */
	public Limit( int max, Ordering ordering ) {
		super();
		
		if ( max < 1 ) throw new IllegalArgumentException( "A limit has to be an 'int' greater than 1 (one)." );
		this.max = max;
		
		if ( ordering == null ) throw new IllegalArgumentException( "Ordering argument cannot be null." );
		this.ordering = ordering;
		
		this.data = new PriorityQueue<>( this.max, this.ordering.getComparator() );
	}
	
	public void add( DataPoint dataPoint ) {
		if ( this.data.contains( dataPoint ) ) return;
		else if ( this.data.size() < this.max )
			this.data.add( dataPoint );
		else if ( this.ordering.getComparator().compare( this.data.peek(), dataPoint ) < 0 ) {
			this.data.poll();
			this.data.add( dataPoint );
		}
	}
	
	/**
	 * The current number of elements got on the Limit structure.
	 * @return
	 */
	public int size() {
		return this.data.size();
	}
	
	/**
	 * This method is responsable for calculating the values from all DataPoint on the current Limit data structure.
	 * @return
	 */
	public double sum() {
		return this.data.parallelStream().map( dataPoint -> dataPoint.getValue() * dataPoint.getMultiplier() ).reduce( ( x, y ) -> x + y ).orElse( 0.0 );
	}

	public DataPoint poll() {
		return this.data.poll();
	}

	@Override
	public String toString() {
		return "\n\tLimit, max = [" + this.max + "], ordering = [" + this.ordering.name() + "]" ;
	}
}
