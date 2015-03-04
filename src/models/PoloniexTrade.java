package models;

import com.google.gson.annotations.SerializedName;

public class PoloniexTrade {

	@SerializedName("rate")
	public double price;
	
	@SerializedName("total")
	double totalQty;
}
