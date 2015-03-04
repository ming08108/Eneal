package models;

import com.google.gson.annotations.SerializedName;

public class CryptsyTicker {

	@SerializedName("success")
	public String success;
	
	@SerializedName("data")
	public Data data;
	
	public class Data{
		
		@SerializedName("last_trade")
		public LastTrade lastTrade;
		
		public class LastTrade{
			@SerializedName("price")
			public double last;
		}
	}
	
	@Override
	public String toString(){
		return "last: " + data.lastTrade.last;
	}
}
