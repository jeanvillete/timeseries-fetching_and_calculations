package com.timeseries.exception;

/**
 * A tag for marking those lines which couldn't be parsed.
 * @author jean
 *
 */
public class InvalidDataPoint extends Exception {

	private static final long serialVersionUID = 7231404599769028983L;

	public InvalidDataPoint() {
		super();
	}
	
	public InvalidDataPoint( String message ) {
		super( message );
	}
	
	public InvalidDataPoint(Throwable cause) {
        super(cause);
    }
	
}
