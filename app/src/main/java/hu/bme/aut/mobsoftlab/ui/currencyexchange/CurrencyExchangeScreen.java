package hu.bme.aut.mobsoftlab.ui.currencyexchange;

public interface CurrencyExchangeScreen {
    void exchange();

    void swapCurrencies();

    void showError(Throwable throwable);
}
