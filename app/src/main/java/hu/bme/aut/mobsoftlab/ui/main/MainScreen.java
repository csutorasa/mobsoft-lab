package hu.bme.aut.mobsoftlab.ui.main;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.model.ExchangeRateWithCurrency;

public interface MainScreen {
    void showFavorites(List<ExchangeRateWithCurrency> rates);

    void showError(Throwable throwable);
}
