package hu.bme.aut.mobsoftlab.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.BuildConfig;
import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.MobSoftApplicationComponent;
import hu.bme.aut.mobsoftlab.TestComponent;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.model.RateWithDate;
import hu.bme.aut.mobsoftlab.repository.Repository;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramPresenter;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramScreen;
import hu.bme.aut.mobsoftlab.utils.RobolectricDaggerTestRunner;

import static hu.bme.aut.mobsoftlab.TestHelper.setTestInjector;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DeleteFavoriteTest {

    @Inject
    protected HistogramPresenter presenter;

    @Inject
    protected Repository repository;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        ((TestComponent)MobSoftApplication.injector).inject(this);
    }

    @Test
    public void testHistogram() {
        Exchange exchange = new Exchange();
        exchange.setFrom("AED");
        exchange.setTo("JPY");
        repository.saveFavorite(exchange);

        HistogramScreen screen = mock(HistogramScreen.class);
        presenter.attachScreen(screen);
        presenter.deleteFavorite("AED", "JPY");

        List<Exchange> favorites = repository.getFavourites();
        for(Exchange fav : favorites) {
            assertFalse(exchange.getFrom().equals(fav.getFrom()) && exchange.getTo().equals(fav.getTo()));
        }
    }

    @After
    public void tearDown() {
        presenter.detachScreen();
    }
}