package hu.bme.aut.mobsoftlab.interactor.favorite.events;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.model.GetRatesResponse;

public class GetRatesEvent {
    private int code;
    private GetRatesResponse response;
    private Throwable throwable;

    public GetRatesEvent() {

    }

    public GetRatesEvent(int code, GetRatesResponse response, Throwable throwable) {
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

    public GetRatesResponse getResponse() {
        return response;
    }

    public void setResponse(GetRatesResponse response) {
        this.response = response;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
