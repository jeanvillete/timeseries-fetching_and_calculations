package com.timeseries.entity;

import java.time.LocalDate;
import java.util.PriorityQueue;

import com.timeseries.entity.enums.Ordering;

public class Limit {
	
	private int max;
	private Ordering ordering;
	private PriorityQueue< LocalDate > data;
	
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
		if ( this.data.contains( dataPoint.getDate() ) ) return;
		
		if ( this.data.size() < this.max ) {
			this.data.add( dataPoint.getDate() );
		} else if ( this.ordering.getComparator().compare( this.data.peek(), dataPoint.getDate() ) < 0 ) {
			this.data.poll();
			this.data.add( dataPoint.getDate() );
		}
	}
	
	public int size() {
		return this.data.size();
	}

	public LocalDate poll() {
		return this.data.poll();
	}

}
