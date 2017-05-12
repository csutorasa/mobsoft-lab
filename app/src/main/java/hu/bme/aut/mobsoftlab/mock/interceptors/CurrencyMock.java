package hu.bme.aut.mobsoftlab.mock.interceptors;

import android.net.Uri;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.bme.aut.mobsoftlab.model.ExchangeRateWithCurrency;
import hu.bme.aut.mobsoftlab.model.GetHistogramResponse;
import hu.bme.aut.mobsoftlab.model.GetRatesResponse;
import hu.bme.aut.mobsoftlab.model.RateWithDate;
import hu.bme.aut.mobsoftlab.network.NetworkConfig;
import hu.bme.aut.mobsoftlab.repository.MemoryRepository;
import hu.bme.aut.mobsoftlab.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.aut.mobsoftlab.mock.interceptors.MockHelper.makeResponse;

public class CurrencyMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "rates") && request.method().equals("GET")) {
            GetRatesResponse response = new GetRatesResponse();
            response.getRates().addAll(getExchangeRates());
            responseString = GsonHelper.getGson().toJson(response);
            responseCode = 200;
        }else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "histogram/") && request.method().equals("GET")) {
            GetHistogramResponse response = new GetHistogramResponse();
            response.getRates().addAll(getHistogramData());
            responseString = GsonHelper.getGson().toJson(response);
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }

    private static List<ExchangeRateWithCurrency> getExchangeRates() {
        List<ExchangeRateWithCurrency> exchangeRates = new ArrayList<>();
        ExchangeRateWithCurrency exchangeRate;

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("HUF");
        exchangeRate.setTo("EUR");
        exchangeRate.setRate(BigDecimal.valueOf(0.03));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("EUR");
        exchangeRate.setTo("HUF");
        exchangeRate.setRate(BigDecimal.valueOf(330));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("HUF");
        exchangeRate.setTo("USD");
        exchangeRate.setRate(BigDecimal.valueOf(0.033));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("USD");
        exchangeRate.setTo("HUF");
        exchangeRate.setRate(BigDecimal.valueOf(300));
        exchangeRates.add(exchangeRate);

        return exchangeRates;
    }

    private static List<RateWithDate> getHistogramData() {
        List<RateWithDate> rates = new ArrayList<>();
        RateWithDate rate;

        rate = new RateWithDate();
        rate.setDate(new Date());
        rate.setRate(BigDecimal.valueOf(1));
        rates.add(rate);

        rate = new RateWithDate();
        rate.setDate(new Date());
        rate.setRate(BigDecimal.valueOf(0.9));
        rates.add(rate);

        rate = new RateWithDate();
        rate.setDate(new Date());
        rate.setRate(BigDecimal.valueOf(1.1));
        rates.add(rate);

        rate = new RateWithDate();
        rate.setDate(new Date());
        rate.setRate(BigDecimal.valueOf(1.2));
        rates.add(rate);

        return rates;
    }
}
