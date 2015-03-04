package Exchanges;

import java.util.ArrayList;

import models.PoloniexTrade;
import models.BittrexHistory.Trade;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import Services.CryptsyService;
import Services.PoloniexService;

public class Poloniex extends Exchange{

	
	PoloniexService service;
	
	
	public Poloniex(){
		super();
		RestAdapter restAdapter = new RestAdapter.Builder()
	    .setEndpoint("https://poloniex.com")
	    .build();
		service = restAdapter.create(PoloniexService.class);
		
		
		
	}
	
	
	@Override
	public double getLast(String market) {
		return service.getAllTickers().getAsJsonObject().getAsJsonObject(convertMarketString(market)).getAsJsonPrimitive("last").getAsDouble();
	}


	@Override
	public String convertMarketString(String stdMarket) {
		return stdMarket.replace("-", "_");
	}


	@Override
	public void updateAllPrices() {
		try{
			JsonObject tickers = service.getAllTickers().getAsJsonObject();
			
			for(String s : MarketStrings.markets){
				ArrayList<Double> list = prices.get(s);
				
				//find the last price
				double lastPrice = tickers.getAsJsonObject(convertMarketString(s)).getAsJsonPrimitive("last").getAsDouble();
				list.add(lastPrice);
			}
		}
		catch(RetrofitError | NullPointerException e){
			System.out.println("Error fetching Poloniex data!");
		}
		
		
	}




	@Override
	public int getTradeHistorySize(String market) {
		return service.getTradeHistory(convertMarketString(market)).size();
	}


	@Override
	public double getVolatilityIndex(String market, int lastTrades) {
		try{
			ArrayList<PoloniexTrade> trades = service.getTradeHistory(convertMarketString(market));
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
	
	public double getAvg(ArrayList<PoloniexTrade> trades){
		double sum = 0;
		for(PoloniexTrade t : trades){
			sum = sum + t.price;
		}
		return sum/trades.size();
	}
	
	

	
}
