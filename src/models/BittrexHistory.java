package models;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class BittrexHistory {

	
	@SerializedName("result")
	public ArrayList<Trade> results;
	
	public class Trade{
		@SerializedName("Price")
		public double price;
		
		@SerializedName("Total")
		public double totalQty;
	}
}
