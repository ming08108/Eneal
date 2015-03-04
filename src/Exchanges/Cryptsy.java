package Exchanges;

import java.util.ArrayList;

import models.BittrexHistory.Trade;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import Services.BittrexService;
import Services.CryptsyService;

public class Cryptsy extends Exchange {

	CryptsyService service;
	
	public Cryptsy(){
		super();
		RestAdapter restAdapter = new RestAdapter.Builder()
	    .setEndpoint("https://api.cryptsy.com")
	    .build();
		service = restAdapter.create(CryptsyService.class);
		
		
	}
	
	
	@Override
	public double getLast(String market) {
		return service.getTicker(convertMarketString(market)).data.lastTrade.last;
	}


	@Override
	public String convertMarketString(String stdMarket) {
		String[] split = stdMarket.split("-");
		return split[1].toLowerCase() + "_" + split[0].toLowerCase();
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
				System.out.println("Error fetching Cryptsy data for: " + s);
				lastPrice = -1;
			}
			
			list.add(lastPrice);
			
		}
		
	}




	@Override
	public int getTradeHistorySize(String market) {
		return service.getTradeHistory(convertMarketString(market)).results.size();
	}


	@Override
	public double getVolatilityIndex(String market, int lastTrades) {
		try{
			ArrayList<models.CryptsyHistory.Trade> trades = service.getTradeHistory(convertMarketString(market)).results;
			
			double sum = 0;
			for(int i = 1; i < lastTrades; i++){
				sum = sum + Math.abs(trades.get(i-1).price - trades.get(i).price);
			}
			
			return sum/(trades.size()* getAvg(trades));
		}
		catch(RetrofitError | NullPointerException e){
			return -1;
		}
	}
	
	public double getAvg(ArrayList<models.CryptsyHistory.Trade> trades){
		double sum = 0;
		for(models.CryptsyHistory.Trade t : trades){
			sum = sum + t.price;
		}
		return sum/trades.size();
	}

}
