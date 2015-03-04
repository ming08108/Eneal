package models;

import com.google.gson.annotations.SerializedName;

public class BittrexTicker {

	@SerializedName("success")
	public String success;
	
	@SerializedName("result")
	public Result result;
	
	public class Result{
		@SerializedName("Bid")
		public double bid;
		
		@SerializedName("Ask")
		public double ask;
		
		@SerializedName("Last")
		public double last;
		
	}
	
	@Override
	public String toString(){
		return "last: " + result.last;
	}
}
