package hu.bme.aut.mobsoftlab.interactor.favorite.events;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;

public class GetFavoritesEvent {
    private int code;
    private List<Exchange> favorites;
    private Throwable throwable;

    public GetFavoritesEvent() {

    }

    public GetFavoritesEvent(int code, List<Exchange> favorites, Throwable throwable) {
        this.code = code;
        this.favorites = favorites;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Exchange> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Exchange> favorites) {
        this.favorites = favorites;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
