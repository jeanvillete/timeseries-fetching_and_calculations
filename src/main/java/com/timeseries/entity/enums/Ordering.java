package com.timeseries.entity.enums;

import java.util.Comparator;

import com.timeseries.entity.DataPoint;

public enum Ordering {

	NEWEST( ( first, second ) -> first.getDate().compareTo( second.getDate() ) ),
	OLDEST( ( first, second ) -> second.getDate().compareTo( first.getDate() ) );
	
	Ordering( Comparator< DataPoint > comparator ) {
		this.comparator = comparator;
	}
	
	private Comparator< DataPoint > comparator;
	
	public Comparator< DataPoint > getComparator() {
		return this.comparator;
	}
	
}
