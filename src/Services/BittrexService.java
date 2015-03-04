package Services;
import com.google.gson.JsonObject;

import retrofit.http.GET;
import retrofit.http.Query;
import models.BittrexHistory;
import models.BittrexTicker;


public interface BittrexService {

	@GET("/api/v1.1/public/getticker")
	BittrexTicker getTicker(@Query("market") String market);
	
	@GET("/api/v1.1/public/getmarkets")
	JsonObject getMarkets();
	
	@GET("/api/v1.1/public/getmarkethistory?count=50")
	BittrexHistory getTradeHistory(@Query("market") String market);
	
}
