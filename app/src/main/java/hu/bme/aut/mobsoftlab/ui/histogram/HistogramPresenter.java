package hu.bme.aut.mobsoftlab.ui.histogram;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
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

    public void loadHistogram() {
        // TODO load histogram from network
        // TODO then show result
    }

    public void deleteFavorite() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // TODO remove real exchange object
                favoritesInteractor.saveFavorite(new Exchange());
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
                // TODO navigate to main screen
            }
        }
    }
}
