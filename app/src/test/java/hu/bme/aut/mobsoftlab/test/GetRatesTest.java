package hu.bme.aut.mobsoftlab.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import hu.bme.aut.mobsoftlab.BuildConfig;
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



    @Before
    public void setup() throws Exception {
        setTestInjector();
    }

    @Test
    public void testTodo() {
    }

    @After
    public void tearDown() {

    }
}