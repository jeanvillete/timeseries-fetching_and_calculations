package com.timeseries.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class InstrumentPriceModifierRepository implements InitializingBean {

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull( this.jdbcTemplate, "Property 'jdbcTemplate' was not properly initialized." );
	}

	@Autowired
	@Qualifier( "dataSource" )
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	public Double getModifier( String instrument ) {
		try {
			return this.jdbcTemplate.queryForObject( " SELECT MULTIPLIER FROM TB_PRICE_MODIFIER WHERE NAME = ? ", new Object[]{ instrument }, Double.class );
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		}
	}
	
}
