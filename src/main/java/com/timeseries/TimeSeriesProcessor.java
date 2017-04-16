package com.timeseries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

import com.timeseries.entity.DataPoint;
import com.timeseries.entity.Query;
import com.timeseries.exception.InvalidDataPoint;

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
	
	public TimeSeriesProcessor process( String fileAddress ) {
		if ( !StringUtils.hasText( fileAddress ) )
			throw new IllegalArgumentException( "Argument 'fileAddress' is mandatory." );
		
		Path path = Paths.get( fileAddress );
		
		if ( !path.toFile().exists() )
			throw new IllegalStateException( "The provided file doesn't exist." );
		if ( !path.toFile().isFile() )
			throw new IllegalStateException( "The file address doesn't point to a file." );
		if ( !path.toFile().canRead() )
			throw new IllegalStateException( "The file is not readable." );
		
		try ( Stream< String > lines = Files.lines( path ) ) {
			lines.forEach( line -> {
				try {
					DataPoint dataPoint = new DataPoint( line );
					// retrieve the multiplier from database
					for ( Query query : this.data.getOrDefault( dataPoint.getInstrument(), new ArrayList< Query >( 0 ) ) )
						query.add( dataPoint );
				} catch ( InvalidDataPoint e ) { } // skip non business date, but also not well formed lines
			});
		} catch ( IOException e ) {
			throw new RuntimeException( "Problems happend while retrieving file; fileAddress=[" + fileAddress + "]" , e );
		}
		return this;
	}

	public void print() {
		this.data.values().forEach( System.out::println );
	}
}