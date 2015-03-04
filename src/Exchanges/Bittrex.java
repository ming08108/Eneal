package Exchanges;
import java.util.ArrayList;
import java.util.Iterator;

import models.BittrexHistory.Trade;

import com.google.gson.JsonElement;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import Services.BittrexService;


public class Bittrex extends Exchange{

	BittrexService service;
	
	
	
	
	public Bittrex(){
		super();
		
		RestAdapter restAdapter = new RestAdapter.Builder()
		.setEndpoint("https://bittrex.com")
		.build();
		service = restAdapter.create(BittrexService.class);

	}
	
	
	@Override
	public double getLast(String market) {
		return service.getTicker(convertMarketString(market)).result.last;
	}

	

	@Override
	public String convertMarketString(String stdMarket) {
		return stdMarket;
	}


	@Override
	public void updateAllPrices() {
		for(String s : MarketStrings.markets){
			ArrayList<Double> list = prices.get(s);
			
			double lastPrice;
			
			
			try{
				lastPrice = getLast(s);
			}
			catch(RetrofitError | NullPointerException e){
				lastPrice = -1;
				System.out.println("Error fetching Cryptsy data for: " + s);
			}
			
			
			list.add(lastPrice);
		}
	}


	@Override
	public double getVolatilityIndex(String market, int lastTrades) {
		try{
			ArrayList<Trade> trades = service.getTradeHistory(convertMarketString(market)).results;
			
			double sum = 0;
			for(int i = 1; i < lastTrades; i++){
				sum = sum + Math.abs(trades.get(i-1).price - trades.get(i).price);
			}
			
			return sum/(trades.size() * getAvg(trades));
		}
		catch(RetrofitError e){
			return -1;
		}
	}
	
	public double getAvg(ArrayList<Trade> trades){
		double sum = 0;
		for(Trade t : trades){
			sum = sum + t.price;
		}
		return sum/trades.size();
	}


	@Override
	public int getTradeHistorySize(String market) {
		return service.getTradeHistory(convertMarketString(market)).results.size();
	}



	
	
	

}
