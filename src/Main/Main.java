package Main;

import java.text.DecimalFormat;

import Exchanges.Bittrex;
import Exchanges.Cryptsy;
import Exchanges.Exchange;
import Exchanges.MarketStrings;
import Exchanges.Poloniex;


public class Main {
	public static void main(String args[]){
		
		Exchange bittrex = new Bittrex();
		Exchange cryptsy = new Cryptsy();
		Poloniex poloniex = new Poloniex();
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		
		
		System.out.format("%20s%20s%20s%20s", "Coin", "Bittrex", "Cryptsy", "Poloniex");
		System.out.println();
		
		
		
		for(String market : MarketStrings.markets){
			
			System.out.format("%20s", market);
			
			double bittrexIndex = bittrex.getVolatilityIndex(market, 50) * 10000;
			double cryptsyIndex = cryptsy.getVolatilityIndex(market, 50) * 10000;
			double poloniexIndex = poloniex.getVolatilityIndex(market, 50) * 10000;
			
			
			System.out.format("%20s", df.format(bittrexIndex));
			

			System.out.format("%20s", df.format(cryptsyIndex));
			

			System.out.format("%20s", df.format(poloniexIndex));


				
			
			
			System.out.println();
		}

		

		
		
		

		
		
	}
}
