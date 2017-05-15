package hu.bme.aut.mobsoftlab.ui.newfavorite;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetFavoritesEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.SaveFavoriteEvent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.ui.Presenter;

import static hu.bme.aut.mobsoftlab.MobSoftApplication.injector;

public class NewFavoritePresenter extends Presenter<NewFavoriteScreen> {

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(NewFavoriteScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void save(final String from, final String to) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
            Exchange exchange = new Exchange();
            exchange.setFrom(from);
            exchange.setTo(to);
            favoritesInteractor.saveFavorite(exchange);
            }
        });
    }

    public void onEventMainThread(SaveFavoriteEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(event.getThrowable());
            }
        } else {
            if (screen != null) {
                screen.navigateBack();
            }
        }
    }
}
