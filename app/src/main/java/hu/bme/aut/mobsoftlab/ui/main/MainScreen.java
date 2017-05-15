package hu.bme.aut.mobsoftlab.ui.main;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;

public interface MainScreen {
    void showFavorites(List<Exchange> exchanges);

    void showError(Throwable throwable);
}
