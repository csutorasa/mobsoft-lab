package hu.bme.aut.mobsoftlab.repository;

import android.content.Context;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;


public interface Repository {

    void open(Context context);

    void close();

    void clear();

    List<Exchange> getFavourites();

    void saveFavorite(Exchange exchange);

    void removeFavorite(Exchange exchange);

    void removeFavorite(String from, String to);

    boolean isFavorite(Exchange exchange);
}
