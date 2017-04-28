package hu.bme.aut.mobsoftlab.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.interactor.favorite.FavoritesInteractor;

@Module
public class InteractorModule {
    @Provides
    public FavoritesInteractor provideFavourites() {
        return new FavoritesInteractor();
    }
}
