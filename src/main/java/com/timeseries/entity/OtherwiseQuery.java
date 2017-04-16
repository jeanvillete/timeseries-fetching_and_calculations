package com.timeseries.entity;

/**
 * This functional interface aims to allow the programmer to specify the 'otherwise' behaviour in case
 * 	an INSTRUMENT is found on the file but no Query was set for this INSTRUMENT.
 * @author jean
 *
 */
@FunctionalInterface
public interface OtherwiseQuery {

	Query get( String instrument );
	
}
