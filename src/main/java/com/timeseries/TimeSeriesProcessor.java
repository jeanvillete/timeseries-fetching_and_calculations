package com.timeseries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.timeseries.entity.Query;

public class TimeSeriesProcessor {

	/**
	 * key = INSTRUMENT
	 * value = Query
	 */
	Map< String, List< Query > > data = new HashMap<>();
	
	public TimeSeriesProcessor( Query... queries ) {
		if ( queries == null || queries.length < 1 )
			throw new IllegalArgumentException( "It is mandatory to provide at least on 'query' to be performed." );
		
		Stream.of( queries ).forEach( query -> {
			List< Query > _queries;
			if ( ( _queries = this.data.get( query.getInstrument() ) ) == null ) {
				_queries = new ArrayList<>();
				this.data.put( query.getInstrument(), _queries );
			}
			_queries.add( query );
		});
	}
}
