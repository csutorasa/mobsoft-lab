package hu.bme.aut.mobsoftlab;


import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.repository.Repository;
import hu.bme.aut.mobsoftlab.ui.UIModule;
import io.fabric.sdk.android.Fabric;

public class MobSoftApplication extends Application {

    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
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
        sAnalytics = GoogleAnalytics.getInstance(this);
        Fabric.with(this, new Crashlytics());


        injector = DaggerMobSoftApplicationComponent.builder().
            uIModule(
                new UIModule(this)
            ).build();
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    synchronized public static Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }
}
