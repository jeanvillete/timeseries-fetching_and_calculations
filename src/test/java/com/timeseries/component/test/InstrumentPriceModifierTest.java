package com.timeseries.component.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.util.Assert;

import com.timeseries.component.InstrumentPriceModifier;
import com.timeseries.repository.InstrumentPriceModifierRepository;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( { "classpath:/spring-test.xml" } )
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class } )
public class InstrumentPriceModifierTest {

	@Autowired
	InstrumentPriceModifierRepository repository;
	
	@Test
	public void betterCase() throws InterruptedException {
		InstrumentPriceModifier component = new InstrumentPriceModifier( repository );
		
		// retrieve from data base because it the first time
		Assert.isTrue( component.getModifier( "INSTRUMENT1" ) == 1.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT2" ) == 2.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT3" ) == 4.5 );
		
		// retrieve from steady cache
		Assert.isTrue( component.getModifier( "INSTRUMENT1" ) == 1.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT2" ) == 2.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT3" ) == 4.5 );
		
		Thread.sleep( 6000L );
		
		// retrieve from database again, since it has passed more than 5 seconds (thread sleeping)
		Assert.isTrue( component.getModifier( "INSTRUMENT1" ) == 1.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT2" ) == 2.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT3" ) == 4.5 );

		// retrieve from steady cache again 
		Assert.isTrue( component.getModifier( "INSTRUMENT1" ) == 1.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT2" ) == 2.5 );
		Assert.isTrue( component.getModifier( "INSTRUMENT3" ) == 4.5 );
	}
	
}
