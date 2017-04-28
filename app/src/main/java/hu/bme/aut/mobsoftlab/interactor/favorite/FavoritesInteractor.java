package hu.bme.aut.mobsoftlab.interactor.favorite;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetFavoritesEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.RemoveFavoriteEvent;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.SaveFavoriteEvent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.repository.Repository;


public class FavoritesInteractor {

    @Inject
    Repository repository;
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
}
