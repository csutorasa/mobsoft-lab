package hu.bme.aut.mobsoftlab.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;

public class MemoryRepository implements Repository {

    public static List<Exchange> favorites;

    @Override
    public void open(Context context) {
        favorites = new ArrayList<>();

        favorites.add(new Exchange("HUF", "EUR"));
        favorites.add(new Exchange("USD", "HUF"));
    }

    @Override
    public void close() {

    }

    @Override
    public void clear() {
        favorites.clear();
    }

    @Override
    public List<Exchange> getFavourites() {
        return favorites;
    }

    @Override
    public void saveFavorite(Exchange favorite) {
        favorites.add(favorite);
    }

    @Override
    public void removeFavorite(Exchange favorite) {
        favorites.remove(favorite);
    }

    @Override
    public void removeFavorite(String from, String to) {
        for (Iterator<Exchange> it = favorites.iterator(); it.hasNext(); ) {
            Exchange e = it.next();
            if(e.getFrom().equals(from) && e.getTo().equals(to)) {
                it.remove();
            }
        }

    }

    @Override
    public boolean isFavorite(Exchange favorite) {
        return favorites.contains(favorite);
    }
}
