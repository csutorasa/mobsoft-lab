package hu.bme.aut.mobsoftlab.ui.main;

import java.util.List;

import hu.bme.aut.mobsoftlab.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {
    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    List<String> getFavorites() {
        return null;
    }
}
