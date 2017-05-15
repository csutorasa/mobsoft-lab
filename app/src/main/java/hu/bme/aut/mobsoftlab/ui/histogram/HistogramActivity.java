package hu.bme.aut.mobsoftlab.ui.histogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.R;
import hu.bme.aut.mobsoftlab.model.RateWithDate;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoriteActivity;

public class HistogramActivity extends AppCompatActivity implements HistogramScreen {
    @Inject
    public HistogramPresenter presenter;

    Button deleteButton;
    ListView histogramList;
    ArrayAdapter<String> histogramAdapter;
    TextView fromText;
    TextView toText;

    String from;
    String to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        to = intent.getStringExtra("to");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);
        MobSoftApplication.injector.inject(this);

        deleteButton = (Button) findViewById(R.id.deleteButton);
        histogramList = (ListView) findViewById(R.id.histogramDataList);
        fromText = (TextView) findViewById(R.id.histogramFromText);
        toText = (TextView) findViewById(R.id.histogramToText);

        histogramAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        histogramList.setAdapter(histogramAdapter);

        fromText.setText(from);
        toText.setText(to);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFavorite();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachScreen(this);

        presenter.loadHistogram(from, to);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachScreen();
    }

    public void deleteFavorite() {
        presenter.deleteFavorite(from, to);
    }

    @Override
    public void navigateBack() {
        finish();
    }


    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHistogram(List<RateWithDate> rates) {
        histogramAdapter.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        for(RateWithDate rate : rates) {
            histogramAdapter.add(sdf.format(rate.getDate()) + " " + rate.getRate().setScale(3, BigDecimal.ROUND_HALF_UP));
        }
        histogramAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.histogram_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.histogramRefresh:
                presenter.loadHistogram(from, to);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
