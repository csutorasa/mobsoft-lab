package hu.bme.aut.mobsoftlab;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoftlab.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;
import hu.bme.aut.mobsoftlab.mock.MockNetworkModule;
import hu.bme.aut.mobsoftlab.network.NetworkModule;
import hu.bme.aut.mobsoftlab.repository.RepositoryModule;
import hu.bme.aut.mobsoftlab.ui.UIModule;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangeActivity;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangePresenter;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramActivity;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramPresenter;
import hu.bme.aut.mobsoftlab.ui.main.MainActivity;
import hu.bme.aut.mobsoftlab.ui.main.MainPresenter;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoriteActivity;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoritePresenter;

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class, MockNetworkModule.class})
public interface MobSoftApplicationComponent {
    void setInjector(MobSoftApplicationComponent appComponent);
    void inject(MobSoftApplication appComponent);
    void inject(MainActivity mainActivity);
    void inject(HistogramActivity histogramActivity);
    void inject(NewFavoriteActivity newFavoriteActivity);
    void inject(CurrencyExchangeActivity currencyExchangeActivity);
    void inject(FavoritesInteractor favoritesInteractor);
    void inject(NewFavoritePresenter newFavoritePresenter);
    void inject(MainPresenter mainPresenter);
    void inject(HistogramPresenter histogramPresenter);
    void inject(CurrencyExchangePresenter currencyExchangePresenter);
}