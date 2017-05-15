package hu.bme.aut.mobsoftlab.ui.currencyexchange;

import java.math.BigDecimal;

public interface CurrencyExchangeScreen {
    void setExchangeRate(BigDecimal d);

    void showError(Throwable throwable);
}
