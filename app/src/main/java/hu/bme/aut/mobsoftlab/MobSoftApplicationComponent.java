package hu.bme.aut.mobsoftlab;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
import hu.bme.aut.mobsoftlab.ui.UIModule;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangeActivity;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramActivity;
import hu.bme.aut.mobsoftlab.ui.main.MainActivity;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoriteActivity;

@Singleton
@Component(modules = {UIModule.class})
public interface MobSoftApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(HistogramActivity histogramActivity);
    void inject(NewFavoriteActivity newFavoriteActivity);
    void inject(CurrencyExchangeActivity currencyExchangeActivity);
    void inject(FavoritesInteractor favoritesInteractor);
}