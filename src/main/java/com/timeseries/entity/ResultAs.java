package com.timeseries.entity;

public abstract class ResultAs {

	private double currentSum = 0.0;
	private Limit limit;
	
	protected ResultAs( Limit limit ) {
		super();
		this.limit = limit;
	}
	
	public abstract double sum();
	
	public void add( DataPoint dataPoint ) {
		if ( this.limit != null ) this.limit.add( dataPoint );
		else this.currentSum += dataPoint.getValue();
	}

	protected double getCurrentSum() {
		return this.currentSum;
	}

	protected Limit getLimit() {
		return this.limit;
	}
	
	public static class Mean extends ResultAs {
		
		protected Mean( Limit limit ) {
			super( limit );
		}
		
		protected Mean() {
			super( null );
		}

		private long nrElements;
		
		@Override
		public void add( DataPoint dataPoint ) {
			if ( this.getLimit() == null ) nrElements++;
			super.add( dataPoint );
		}

		@Override
		public double sum() {
			if ( this.getLimit() != null ) return this.getLimit().sum() / this.getLimit().size();
			else return this.nrElements > 0.0 ? this.getCurrentSum() / this.nrElements : 0.0;
		}
	}
	
	public static class Sum extends ResultAs {
		
		protected Sum( Limit limit ) {
			super( limit );
		}
		
		protected Sum() {
			super( null );
		}

		@Override
		public double sum() {
			return this.getLimit() != null ? this.getLimit().sum() : this.getCurrentSum();
		}
	}
}
