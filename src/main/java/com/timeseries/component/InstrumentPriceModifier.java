package com.timeseries.component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.timeseries.repository.InstrumentPriceModifierRepository;

public class InstrumentPriceModifier {
	
	private static final int SECONDS_TO_REQUEST_AGAIN = 5;
	
	private InstrumentPriceModifierRepository repository;
	private Map< String, PriceModifierWrapper > data = new HashMap<>();
	

	public InstrumentPriceModifier( InstrumentPriceModifierRepository repository ) {
		if ( repository == null )
			throw new IllegalArgumentException( "Argument 'repository' is mandatory." );
		this.repository = repository;
	}
	
	public Double getModifier( String instrument ) {
		PriceModifierWrapper priceModifierWrapper;
		if ( ( priceModifierWrapper = this.data.get( instrument ) ) == null || priceModifierWrapper.lastAccess.isBefore( LocalDateTime.now().minusSeconds( SECONDS_TO_REQUEST_AGAIN ) ) ) {
			priceModifierWrapper = new PriceModifierWrapper( this.repository.getModifier( instrument ), LocalDateTime.now() );
			this.data.put( instrument, priceModifierWrapper );
		}
		
		return priceModifierWrapper.modifier;
	}

	private class PriceModifierWrapper {
		
		private Double modifier;
		private LocalDateTime lastAccess;
		
		PriceModifierWrapper( Double modifier, LocalDateTime lastAccess ) {
			this.modifier = modifier;
			this.lastAccess = lastAccess;
		}
	}
	
}
