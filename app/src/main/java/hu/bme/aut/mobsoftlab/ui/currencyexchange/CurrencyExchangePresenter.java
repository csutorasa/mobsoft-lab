package hu.bme.aut.mobsoftlab.ui.currencyexchange;

import android.icu.text.TimeZoneFormat;
import android.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetRatesEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.SaveFavoriteEvent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.model.ExchangeRateWithCurrency;
import hu.bme.aut.mobsoftlab.model.GetRatesResponse;
import hu.bme.aut.mobsoftlab.network.currency.CurrencyApi;
import hu.bme.aut.mobsoftlab.ui.Presenter;

import static hu.bme.aut.mobsoftlab.MobSoftApplication.injector;

public class CurrencyExchangePresenter extends Presenter<CurrencyExchangeScreen> {

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(CurrencyExchangeScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    void getExchangeRate(String from, String to) {
        favoritesInteractor.getRates(Arrays.asList(new Pair<String, String>(from ,to)));
    }

    public void onEventMainThread(GetRatesEvent event) {
        List<ExchangeRateWithCurrency> rates = event.getResponse().getRates();
        if(!rates.isEmpty()) {
            screen.setExchangeRate(rates.get(0).getRate());
        }
    }
}
