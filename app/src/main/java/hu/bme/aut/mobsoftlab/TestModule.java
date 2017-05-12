package hu.bme.aut.mobsoftlab;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

import hu.bme.aut.mobsoftlab.ui.UIModule;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangePresenter;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramPresenter;
import hu.bme.aut.mobsoftlab.ui.main.MainPresenter;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoritePresenter;
import hu.bme.aut.mobsoftlab.utils.UiExecutor;

@Module
public class TestModule {

    private final UIModule UIModule;

    public TestModule(Context context) {
        this.UIModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return UIModule.provideContext();
    }


    @Provides
    public MainPresenter provideMainPresenter() {
        return UIModule.provideMainPresenter();
    }

    @Provides
    public HistogramPresenter provideHistogramPresenter() {
        return UIModule.provideHistogramPresenter();
    }

    @Provides
    public NewFavoritePresenter provideNewFavoritePresenter() {
        return UIModule.provideNewFavoritePresenter();
    }

    @Provides
    public CurrencyExchangePresenter provideCurrencyExchangePresenter() {
        return UIModule.provideCurrencyExchangePresenter();
    }


    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }


}