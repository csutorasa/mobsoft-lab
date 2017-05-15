package hu.bme.aut.mobsoftlab.interactor.favorite;

import android.text.TextUtils;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetFavoritesEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetHistogramEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetRatesEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.RemoveFavoriteEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.SaveFavoriteEvent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.model.GetHistogramResponse;
import hu.bme.aut.mobsoftlab.model.GetRatesResponse;
import hu.bme.aut.mobsoftlab.network.currency.CurrencyApi;
import hu.bme.aut.mobsoftlab.repository.Repository;
import retrofit2.Response;


public class FavoritesInteractor {

    @Inject
    Repository repository;
    @Inject
    CurrencyApi currencyApi;
    @Inject
    EventBus bus;

    public FavoritesInteractor() {
        MobSoftApplication.injector.inject(this);
    }

    public void getFavorites() {
        GetFavoritesEvent event = new GetFavoritesEvent();
        try {
            List<Exchange> favorites = repository.getFavourites();
            event.setFavorites(favorites);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void saveFavorite(Exchange exchange) {

        SaveFavoriteEvent event = new SaveFavoriteEvent();
        event.setFavorite(exchange);
        try {
            repository.saveFavorite(exchange);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void removeFavorite(Exchange exchange) {
        RemoveFavoriteEvent event = new RemoveFavoriteEvent();
        event.setFavorite(exchange);
        try {
            repository.removeFavorite(exchange);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void removeFavorite(String from, String to) {
        RemoveFavoriteEvent event = new RemoveFavoriteEvent();
        try {
            repository.removeFavorite(from, to);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getRates(List<Pair<String, String>> filter) {
        GetRatesEvent event = new GetRatesEvent();
        List<String> filterPairs = new ArrayList<>();
        for (Pair<String, String> fromTo : filter) {
            filterPairs.add(fromTo.first + "-" + fromTo.second);
        }
        String filterString = TextUtils.join(",", filterPairs);

        try {
            Response<GetRatesResponse> response = currencyApi.getRates(filterString).execute();
            switch(response.code()) {
                case 200:
                    GetRatesResponse ratesResponse = response.body();
                    event.setResponse(ratesResponse);
                    break;
                case 400:
                    event.setThrowable(new Exception("Invalid filter or currency"));
                    break;
                default:
                    event.setThrowable(new Exception("Unknown error"));
                    break;
            }
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getHistogram(String from, String to) {
        GetHistogramEvent event = new GetHistogramEvent();
        try {
            Response<GetHistogramResponse> response = currencyApi.getHistogram(from, to).execute();
            GetHistogramResponse histogramResponse = response.body();
            event.setResponse(histogramResponse);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
