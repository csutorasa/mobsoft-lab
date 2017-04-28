package hu.bme.aut.mobsoftlab.interactor.favorite.events;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;

public class SaveFavoriteEvent {
    private int code;
    private Exchange favorite;
    private Throwable throwable;

    public SaveFavoriteEvent() {

    }

    public SaveFavoriteEvent(int code, Exchange favorite, Throwable throwable) {
        this.code = code;
        this.favorite = favorite;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Exchange getFavorite() {
        return favorite;
    }

    public void setFavorite(Exchange favorite) {
        this.favorite = favorite;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
