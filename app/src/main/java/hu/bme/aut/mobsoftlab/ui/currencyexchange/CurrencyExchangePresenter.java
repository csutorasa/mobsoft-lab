package hu.bme.aut.mobsoftlab.ui.currencyexchange;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.SaveFavoriteEvent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.model.GetRatesResponse;
import hu.bme.aut.mobsoftlab.network.currency.CurrencyApi;
import hu.bme.aut.mobsoftlab.ui.Presenter;

import static hu.bme.aut.mobsoftlab.MobSoftApplication.injector;

public class CurrencyExchangePresenter extends Presenter<CurrencyExchangeScreen> {

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    CurrencyApi currencyApi;

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
        // TODO get currency rates from network
        // List<GetRatesResponse> rates = currencyApi.getRates(from + "=" + to);
    }

    public void onEventMainThread(Object event) {
    }
}
