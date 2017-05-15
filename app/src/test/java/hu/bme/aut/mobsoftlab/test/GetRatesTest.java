package hu.bme.aut.mobsoftlab.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.annotation.Config;

import java.math.BigDecimal;
import java.util.List;

import hu.bme.aut.mobsoftlab.BuildConfig;
import hu.bme.aut.mobsoftlab.model.RateWithDate;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangeActivity;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangePresenter;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangeScreen;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramPresenter;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramScreen;
import hu.bme.aut.mobsoftlab.ui.main.MainPresenter;
import hu.bme.aut.mobsoftlab.ui.main.MainScreen;
import hu.bme.aut.mobsoftlab.utils.RobolectricDaggerTestRunner;

import static hu.bme.aut.mobsoftlab.TestHelper.setTestInjector;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class GetRatesTest {

    protected CurrencyExchangePresenter presenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        presenter = new CurrencyExchangePresenter();
    }

    @Test
    public void testRates() {
        CurrencyExchangeScreen screen = mock(CurrencyExchangeScreen.class);
        presenter.attachScreen(screen);
        presenter.getExchangeRate("EUR", "HUF");

        ArgumentCaptor<BigDecimal> captor = ArgumentCaptor.forClass(BigDecimal.class);
        verify(screen).setExchangeRate(captor.capture());

        List<BigDecimal> capturedValues = captor.getAllValues();

        Assert.assertEquals(1, capturedValues.size());
        BigDecimal rate = capturedValues.get(0);
        Assert.assertEquals(300.0, rate.doubleValue(), 50.0);
    }

    @After
    public void tearDown() {
        presenter.detachScreen();
    }
}