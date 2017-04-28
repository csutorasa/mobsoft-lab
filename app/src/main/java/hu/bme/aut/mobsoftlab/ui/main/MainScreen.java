package hu.bme.aut.mobsoftlab.ui.main;

import java.util.List;

public interface MainScreen {
    void getFavorites();

    void showError(Throwable throwable);
}
