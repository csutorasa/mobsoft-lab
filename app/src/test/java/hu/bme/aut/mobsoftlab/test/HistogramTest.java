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
import hu.bme.aut.mobsoftlab.model.GetHistogramResponse;
import hu.bme.aut.mobsoftlab.model.RateWithDate;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramPresenter;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramScreen;
import hu.bme.aut.mobsoftlab.ui.main.MainPresenter;
import hu.bme.aut.mobsoftlab.ui.main.MainScreen;
import hu.bme.aut.mobsoftlab.utils.RobolectricDaggerTestRunner;

import static hu.bme.aut.mobsoftlab.TestHelper.setTestInjector;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class HistogramTest {

    protected HistogramPresenter presenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        presenter = new HistogramPresenter();
    }

    @Test
    public void testHistogram() {
        HistogramScreen screen = mock(HistogramScreen.class);
        presenter.attachScreen(screen);
        presenter.loadHistogram("HUF", "EUR");

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(screen).showHistogram(captor.capture());

        List<List> capturedValues = captor.getAllValues();

        assertEquals(1, capturedValues.size());
        List<RateWithDate> rates = capturedValues.get(0);
        for(RateWithDate r : rates) {
            assertEquals(1.0, r.getRate().doubleValue(), 1.0);
        }
    }

    @After
    public void tearDown() {
        presenter.detachScreen();
    }
}