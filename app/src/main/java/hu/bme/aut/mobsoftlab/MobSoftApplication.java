package hu.bme.aut.mobsoftlab;


import android.app.Application;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.repository.Repository;
import hu.bme.aut.mobsoftlab.ui.UIModule;

public class MobSoftApplication extends Application {

    public static MobSoftApplicationComponent injector;

    @Inject
    Repository repository;

    public void setInjector(MobSoftApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerMobSoftApplicationComponent.builder().
            uIModule(
                new UIModule(this)
            ).build();
        injector.inject(this);
        repository.open(getApplicationContext());
    }
}
