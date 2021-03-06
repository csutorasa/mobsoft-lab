package hu.bme.aut.mobsoftlab.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import hu.bme.aut.mobsoftlab.model.Exchange;

public class SugarOrmRepository implements Repository {
    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public void clear() {
        SugarRecord.deleteAll(Exchange.class);
    }

    @Override
    public List<Exchange> getFavourites() {
        return SugarRecord.listAll(Exchange.class);
    }

    @Override
    public void saveFavorite(Exchange exchange) {
        SugarRecord.saveInTx(exchange);
    }

    @Override
    public void removeFavorite(Exchange exchange) {
        SugarRecord.deleteInTx(exchange);
    }

    public void removeFavorite(String from, String to) {
        SugarRecord.deleteAll(Exchange.class, "from = ? and to = ?", from, to);
    }

    @Override
    public boolean isFavorite(Exchange exchange) {
        return SugarRecord.findById(Exchange.class, exchange.getId()) != null;
    }
}
