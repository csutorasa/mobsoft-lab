package hu.bme.aut.mobsoftlab.ui.main;

import android.util.Pair;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinWorkerThread;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetFavoritesEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetRatesEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.SaveFavoriteEvent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.network.currency.CurrencyApi;
import hu.bme.aut.mobsoftlab.ui.Presenter;

import static hu.bme.aut.mobsoftlab.MobSoftApplication.injector;

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    void getFavorites() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favoritesInteractor.getFavorites();
            }
        });
    }

    public void onEventMainThread(GetFavoritesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(event.getThrowable());
            }
        } else {
            if (screen != null) {
                getFavoriteRates(event.getFavorites());
            }
        }
    }

    void getFavoriteRates(List<Exchange> favorites) {
        final List<Pair<String, String>> filter = new ArrayList<>();
        for(Exchange fav : favorites) {
            filter.add(new Pair<String, String>(fav.getFrom(), fav.getTo()));
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                favoritesInteractor.getRates(filter);
            }
        });
    }

    public void onEventMainThread(GetRatesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(event.getThrowable());
            }
        } else {
            if (screen != null) {
                screen.showFavorites(event.getResponse().getRates());
            }
        }
    }
}
