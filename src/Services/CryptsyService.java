package Services;

import models.CryptsyHistory;
import models.CryptsyTicker;
import retrofit.http.GET;
import retrofit.http.Path;


public interface CryptsyService {
	
	
	@GET("/api/v2/markets/{market}")
	CryptsyTicker getTicker(@Path("market") String market);
	
	@GET("/api/v2/markets/{market}/tradehistory")
	CryptsyHistory getTradeHistory(@Path("market") String market);
	
	
}
