package hu.bme.aut.mobsoftlab.ui.main;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.R;
import hu.bme.aut.mobsoftlab.model.Exchange;
import hu.bme.aut.mobsoftlab.model.ExchangeRateWithCurrency;
import hu.bme.aut.mobsoftlab.ui.currencyexchange.CurrencyExchangeActivity;
import hu.bme.aut.mobsoftlab.ui.histogram.HistogramActivity;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoriteActivity;

public class MainActivity extends AppCompatActivity implements MainScreen {

    private static final String EXCHANGE = "Exchange";

    @Inject
    protected MainPresenter mainPresenter;

    ListView favoritesListView;
    ArrayAdapter<String> favoritesAdapter;
    ListView sidebar;
    ArrayAdapter<String> sidebarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobSoftApplication.injector.inject(this);

        favoritesListView = (ListView) findViewById(R.id.mainFavoritesListView);
        sidebar = (ListView) findViewById(R.id.sidebar);

        favoritesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        favoritesListView.setAdapter(favoritesAdapter);

        sidebarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        sidebarAdapter.add(EXCHANGE);
        sidebar.setAdapter(sidebarAdapter);

        favoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = favoritesAdapter.getItem(position);
                String[] words = text.split(" ");
                String from = words[0];
                String to = words[2];
                Intent intent = new Intent(MainActivity.this, HistogramActivity.class);
                intent.putExtra("from", from);
                intent.putExtra("to", to);
                startActivity(intent);
            }
        });

        sidebar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String menu = sidebarAdapter.getItem(position);
                switch (menu) {
                    case EXCHANGE:
                        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
                        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                        if(!CurrencyExchangeActivity.class.getName().equals(cn.getClassName())) {
                            startActivity(new Intent(MainActivity.this, CurrencyExchangeActivity.class));
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);

        refreshFavorites();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.newfavorite:
                startActivity(new Intent(this, NewFavoriteActivity.class));
                return true;
            case R.id.refresh:
                refreshFavorites();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void refreshFavorites() {
        mainPresenter.getFavorites();
    }

    public void showFavorites(List<ExchangeRateWithCurrency> rates) {
        favoritesAdapter.clear();
        for (ExchangeRateWithCurrency e : rates) {
            favoritesAdapter.add(e.getFrom() + " - " + e.getTo() + " " + e.getRate().setScale(3, BigDecimal.ROUND_HALF_UP));
        }
        favoritesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
