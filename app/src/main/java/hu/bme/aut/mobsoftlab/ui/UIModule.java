package hu.bme.aut.mobsoftlab.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangePresenter;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramPresenter;
import hu.bme.aut.mobsoftlab.ui.main.MainPresenter;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoritePresenter;

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public CurrencyExchangePresenter provideCurrencyExchangePresenter() {
        return new CurrencyExchangePresenter();
    }

    @Provides
    @Singleton
    public NewFavoritePresenter provideNewFavoritePresenter() {
        return new NewFavoritePresenter();
    }

    @Provides
    @Singleton
    public HistogramPresenter provideHistogramPresenter() {
        return new HistogramPresenter();
    }
}
