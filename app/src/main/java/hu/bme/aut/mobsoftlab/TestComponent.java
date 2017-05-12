package hu.bme.aut.mobsoftlab;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoftlab.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.mock.MockNetworkModule;
import hu.bme.aut.mobsoftlab.repository.TestRepositoryModule;

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends MobSoftApplicationComponent {
}