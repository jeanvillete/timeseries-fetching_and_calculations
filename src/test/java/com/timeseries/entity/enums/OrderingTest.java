package com.timeseries.entity.enums;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.util.Assert;

import com.timeseries.entity.DataPoint;

public class OrderingTest {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern( DataPoint.LOCAL_DATE_PATTERN );
	
	public static final String[] unordered = new String[]{	
		"05-Jan-1996",
		"01-Jan-1996", 
		"15-Jan-1996", 
		"13-Jan-1996",
		"14-Apr-1996",
		"01-Feb-1996",
		"03-Jan-1996", 
		"02-Jan-1996", 
		"30-Mar-1996", 
		"04-Jan-1996" };
	
	public static final String[] newestAsPriorityQueue = new String[]{
		"01-Jan-1996",
		"02-Jan-1996",
		"03-Jan-1996",
		"04-Jan-1996",
		"05-Jan-1996",
		"13-Jan-1996",
		"15-Jan-1996",
		"01-Feb-1996",
		"30-Mar-1996",
		"14-Apr-1996" };
	
	public static final String[] oldestAsPriorityQueue = new String[]{
		"14-Apr-1996",
		"30-Mar-1996",
		"01-Feb-1996",
		"15-Jan-1996",
		"13-Jan-1996",
		"05-Jan-1996",
		"04-Jan-1996",
		"03-Jan-1996",
		"02-Jan-1996",
		"01-Jan-1996" };

	@Test
	public void newest() {
		PriorityQueue< LocalDate > pq = new PriorityQueue<>( Ordering.NEWEST.getComparator() );
		Stream.of( unordered ).forEach( date -> pq.offer( LocalDate.parse( date, FORMATTER ) ) );
		
		LocalDate ld;
		for ( int i = 0; ( ld = pq.poll() ) != null; i++ )
			Assert.isTrue( ld.isEqual( LocalDate.parse( newestAsPriorityQueue[ i ], FORMATTER ) ) );
	}
	
	@Test
	public void oldest() {
		PriorityQueue< LocalDate > pq = new PriorityQueue<>( Ordering.OLDEST.getComparator() );
		Stream.of( unordered ).forEach( date -> pq.offer( LocalDate.parse( date, FORMATTER ) ) );
		
		LocalDate ld;
		for ( int i = 0; ( ld = pq.poll() ) != null; i++ )
			Assert.isTrue( ld.isEqual( LocalDate.parse( oldestAsPriorityQueue[ i ], FORMATTER ) ) );
	}
	
}
