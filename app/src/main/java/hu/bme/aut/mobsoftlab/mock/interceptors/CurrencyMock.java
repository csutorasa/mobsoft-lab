package hu.bme.aut.mobsoftlab.mock.interceptors;

import android.net.Uri;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

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
    private static Random rnd = new Random();

    private static final int OK = 200;
    private static final int BAD_REQUEST = 400;

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "rates") && request.method().equals("GET")) {
            GetRatesResponse response = new GetRatesResponse();
            List<ExchangeRateWithCurrency> allRates = getExchangeRates();
            String x = uri.getPath();

            String params = uri.getQueryParameter("filter");
            if(params.length() != 0) {
                List<ExchangeRateWithCurrency> filteredRates = new ArrayList<>();
                String[] exchanges = params.split(",");
                for (String exchange : exchanges) {
                    String[] fromto = exchange.split("-");
                    if(fromto.length == 2) {
                        boolean found = false;
                        for (ExchangeRateWithCurrency exchangeRate : allRates) {
                            if(exchangeRate.getFrom().equals(fromto[0]) && exchangeRate.getTo().equals(fromto[1])) {
                                filteredRates.add(exchangeRate);
                                found = true;
                                break;
                            }
                        }
                        if(!found) {
                            responseString = "Invalid filter";
                            responseCode = BAD_REQUEST;
                        }
                    } else {
                        responseString = "Invalid filter";
                        responseCode = BAD_REQUEST;
                    }
                }
                response.setRates(filteredRates);
                responseString = GsonHelper.getGson().toJson(response);
                responseCode = OK;
            } else {
                response.setRates(allRates);
                responseString = GsonHelper.getGson().toJson(response);
                responseCode = OK;
            }
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "histogram/") && request.method().equals("GET")) {
            GetHistogramResponse response = new GetHistogramResponse();
            response.getRates().addAll(getHistogramData());
            responseString = GsonHelper.getGson().toJson(response);
            responseCode = OK;
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
        exchangeRate.setRate(getRate(0.03));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("EUR");
        exchangeRate.setTo("HUF");
        exchangeRate.setRate(getRate(330));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("HUF");
        exchangeRate.setTo("USD");
        exchangeRate.setRate(getRate(0.033));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("USD");
        exchangeRate.setTo("HUF");
        exchangeRate.setRate(getRate(300));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("EUR");
        exchangeRate.setTo("USD");
        exchangeRate.setRate(getRate(1.1));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("USD");
        exchangeRate.setTo("EUR");
        exchangeRate.setRate(getRate(0.9));
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("EUR");
        exchangeRate.setTo("EUR");
        exchangeRate.setRate(BigDecimal.ONE);
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("USD");
        exchangeRate.setTo("USD");
        exchangeRate.setRate(BigDecimal.ONE);
        exchangeRates.add(exchangeRate);

        exchangeRate = new ExchangeRateWithCurrency();
        exchangeRate.setFrom("HUF");
        exchangeRate.setTo("HUF");
        exchangeRate.setRate(BigDecimal.ONE);
        exchangeRates.add(exchangeRate);

        return exchangeRates;
    }

    private static List<RateWithDate> getHistogramData() {
        List<RateWithDate> rates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        RateWithDate rate;

        rate = new RateWithDate();
        rate.setDate(calendar.getTime());
        rate.setRate(getRate(1));
        rates.add(rate);
        calendar.add(Calendar.DATE, -1);

        rate = new RateWithDate();
        rate.setDate(calendar.getTime());
        rate.setRate(getRate(0.9));
        rates.add(rate);
        calendar.add(Calendar.DATE, -1);

        rate = new RateWithDate();
        rate.setDate(calendar.getTime());
        rate.setRate(getRate(1.1));
        rates.add(rate);
        calendar.add(Calendar.DATE, -1);

        rate = new RateWithDate();
        rate.setDate(calendar.getTime());
        rate.setRate(getRate(1.2));
        rates.add(rate);

        return rates;
    }

    private static BigDecimal getRate(double rate) {
        double randomness = 0.1;
        return new BigDecimal(rate * (rnd.nextDouble() * randomness * 2 + 1 - randomness));
    }
}
