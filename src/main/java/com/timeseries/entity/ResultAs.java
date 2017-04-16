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
	
	@Override
	public String toString() {
		return ( this.limit != null ? this.limit.toString() : "" ) +
				"\n\tResult As";
	}
	
	public static class Mean extends ResultAs {
		
		public Mean( Limit limit ) {
			super( limit );
		}
		
		public Mean() {
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
		
		@Override
		public String toString() {
			return super.toString() + " MEAN";
		}
	}
	
	public static class Sum extends ResultAs {
		
		public Sum( Limit limit ) {
			super( limit );
		}
		
		public Sum() {
			super( null );
		}

		@Override
		public double sum() {
			return this.getLimit() != null ? this.getLimit().sum() : this.getCurrentSum();
		}
		
		@Override
		public String toString() {
			return super.toString() + " SUM";
		}
	}
}
