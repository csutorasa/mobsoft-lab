package hu.bme.aut.mobsoftlab.ui.histogram;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetHistogramEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.RemoveFavoriteEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.SaveFavoriteEvent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.ui.Presenter;

import static hu.bme.aut.mobsoftlab.MobSoftApplication.injector;

public class HistogramPresenter extends Presenter<HistogramScreen> {

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(HistogramScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void loadHistogram(final String from, final String to) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favoritesInteractor.getHistogram(from, to);
            }
        });
    }

    public void deleteFavorite(final String from, final String to) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                favoritesInteractor.removeFavorite(from, to);
            }
        });
    }

    public void onEventMainThread(RemoveFavoriteEvent event) {
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

    public void onEventMainThread(GetHistogramEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showError(event.getThrowable());
            }
        } else {
            if (screen != null) {
                screen.showHistogram(event.getResponse().getRates());
            }
        }
    }
}
