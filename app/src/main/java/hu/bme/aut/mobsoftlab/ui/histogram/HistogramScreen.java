package hu.bme.aut.mobsoftlab.ui.histogram;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.RateWithDate;

public interface HistogramScreen {
    void navigateBack();

    void showError(Throwable throwable);

    void showHistogram(List<RateWithDate> rates);
}
