package hu.bme.aut.mobsoftlab.mock;

import java.util.Arrays;
import java.util.List;

public class MockCurrencies {
    public static List<String> getCurrencies() {
        return Arrays.asList(
            "HUF",
            "EUR",
            "USD"
        );
    }
}
