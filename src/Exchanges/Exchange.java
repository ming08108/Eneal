package Exchanges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Exchange {
	
	
	HashMap<String, ArrayList<Double>> prices = new HashMap<String, ArrayList<Double>>();
	
	public Exchange(){
		initMap();
	}
	
	/*
	 * Fills the map with the marketStrings
	 */
	public void initMap(){
		for(String s : MarketStrings.markets){
			prices.put(s, new ArrayList<Double>());
		}
		
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		
		for (Entry<String, ArrayList<Double>> entry : prices.entrySet()) {
		    sb.append("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
		return sb.toString();
		
	}
	
	
	public abstract double getLast(String market);
	
	
	
	/*
	 * Converts from the bittrex market string (Standard) to the required market string
	 */
	public abstract String convertMarketString(String stdMarket);
	
	
	/*
	 * Update the arraylists for all the markets
	 */
	public abstract void updateAllPrices();
	
	
	public abstract double getVolatilityIndex(String market , int lastTrades);
	
	public abstract int getTradeHistorySize(String market);
	
	
	
	
}
