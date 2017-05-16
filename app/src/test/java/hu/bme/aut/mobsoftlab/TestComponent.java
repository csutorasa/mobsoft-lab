package hu.bme.aut.mobsoftlab;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoftlab.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.interactor.favorite.events.GetRatesEvent;
import hu.bme.aut.mobsoftlab.mock.MockNetworkModule;
import hu.bme.aut.mobsoftlab.repository.TestRepositoryModule;
import hu.bme.aut.mobsoftlab.test.DeleteFavoriteTest;
import hu.bme.aut.mobsoftlab.test.GetRatesTest;
import hu.bme.aut.mobsoftlab.test.HistogramTest;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends MobSoftApplicationComponent {
    void inject(DeleteFavoriteTest test);
    void inject(GetRatesTest test);
    void inject(HistogramTest test);
}