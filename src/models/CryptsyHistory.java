package models;

import java.util.ArrayList;

import models.BittrexHistory.Trade;

import com.google.gson.annotations.SerializedName;

public class CryptsyHistory {

	@SerializedName("data")
	public ArrayList<Trade> results;
	
	
	public class Trade{
		@SerializedName("tradeprice")
		public double price;
		
		@SerializedName("total")
		double totalQty;
	}
}
