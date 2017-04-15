package com.timeseries.entity.enums;

import java.time.LocalDate;
import java.util.Comparator;

public enum Ordering {

	NEWEST( ( first, second ) -> first.compareTo( second ) ),
	OLDEST( ( first, second ) -> second.compareTo( first ) );
	
	Ordering( Comparator< LocalDate > comparator ) {
		this.comparator = comparator;
	}
	
	private Comparator< LocalDate > comparator;
	
	public Comparator< LocalDate > getComparator() {
		return this.comparator;
	}
	
}
