package hu.bme.aut.mobsoftlab.network.currency;

import hu.bme.aut.mobsoftlab.model.GetHistogramResponse;
import hu.bme.aut.mobsoftlab.model.GetRatesResponse;


import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CurrencyApi {
  
  /**
   * 
   * Gets the historical rates of the currencies.
   * @param from From currency
   * @param to To currency
   * @return Call<GetHistogramResponse>
   */
  
  @GET("histogram/{from}/{to}")
  Call<GetHistogramResponse> getHistogram(
          @Path("from") String from, @Path("to") String to
  );

  
  /**
   * 
   * Gets the rates of the currencies
   * @param filter Filters the from and to currencies. Comma separated hyphen connected values. Example: ?filter=EUR-HUF,EUR-USD
   * @return Call<GetRatesResponse>
   */
  
  @GET("rates")
  Call<GetRatesResponse> getRates(
          @Query("filter") String filter
  );

  
}
