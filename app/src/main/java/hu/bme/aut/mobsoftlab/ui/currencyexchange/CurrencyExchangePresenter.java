package hu.bme.aut.mobsoftlab.ui.currencyexchange;

import hu.bme.aut.mobsoftlab.ui.Presenter;

public class CurrencyExchangePresenter extends Presenter<CurrencyExchangeScreen> {
    @Override
    public void attachScreen(CurrencyExchangeScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    double getExchangeRate() {
        return 0;
    }
}
