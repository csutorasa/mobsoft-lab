package hu.bme.aut.mobsoftlab.interactor.favorite.events;

import hu.bme.aut.mobsoftlab.model.GetHistogramResponse;
import hu.bme.aut.mobsoftlab.model.GetRatesResponse;

public class GetHistogramEvent {
    private int code;
    private GetHistogramResponse response;
    private Throwable throwable;

    public GetHistogramEvent() {

    }

    public GetHistogramEvent(int code, GetHistogramResponse response, Throwable throwable) {
        this.code = code;
        this.response = response;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public GetHistogramResponse getResponse() {
        return response;
    }

    public void setResponse(GetHistogramResponse response) {
        this.response = response;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
