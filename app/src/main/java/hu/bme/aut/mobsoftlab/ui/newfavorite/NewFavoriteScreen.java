package hu.bme.aut.mobsoftlab.ui.newfavorite;

public interface NewFavoriteScreen {
    void save();

    void swapCurrencies();

    void showError(Throwable throwable);
}
