package Services;

import java.util.ArrayList;

import com.google.gson.JsonElement;

import models.BittrexTicker;
import models.PoloniexTrade;
import retrofit.http.GET;
import retrofit.http.Query;

public interface PoloniexService {
	
	@GET("/public?command=returnTicker")
	JsonElement getAllTickers();
	
	
	@GET("/public?command=returnTradeHistory")
	ArrayList<PoloniexTrade> getTradeHistory(@Query("currencyPair") String market);
	
	

}
