package hu.bme.aut.mobsoftlab.ui.currencyexchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.R;
import hu.bme.aut.mobsoftlab.mock.MockCurrencies;
import hu.bme.aut.mobsoftlab.ui.newfavorite.NewFavoriteActivity;

public class CurrencyExchangeActivity extends AppCompatActivity implements CurrencyExchangeScreen {
    @Inject
    protected CurrencyExchangePresenter presenter;

    String from;
    String to;
    double value;
    BigDecimal rate;

    EditText inputText;
    TextView resultText;
    Button swapButton;
    ListView sourceListView;
    ArrayAdapter<String> sourceAdapter;
    ListView targetListView;
    ArrayAdapter<String> targetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        MobSoftApplication.injector.inject(this);

        sourceListView = (ListView) findViewById(R.id.exchangeSourceList);
        targetListView = (ListView) findViewById(R.id.exchangeTargetList);
        swapButton = (Button) findViewById(R.id.exchangeswapButton);
        inputText = (EditText) findViewById(R.id.exchangeSourceAmountText);
        resultText = (TextView) findViewById(R.id.exchangeTargetAmountText);

        sourceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        sourceAdapter.addAll(MockCurrencies.getCurrencies());
        sourceListView.setAdapter(sourceAdapter);
        targetAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        targetAdapter.addAll(MockCurrencies.getCurrencies());
        targetListView.setAdapter(targetAdapter);

        sourceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                from = sourceAdapter.getItem(position);
                exchange();
                setResultText();
            }
        });

        targetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                to = targetAdapter.getItem(position);
                exchange();
                setResultText();
            }
        });

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapCurrencies();
                exchange();
            }
        });

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    value = Double.parseDouble(inputText.getText().toString());
                    exchange();
                    setResultText();
                } catch(NumberFormatException e) { }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setResultText() {
        if(from != null && to != null) {
            resultText.setText(value + " " + from + " = " + (rate.doubleValue() * value) + to);
        } else {
            resultText.setText("");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachScreen(this);

        from = null;
        to = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachScreen();
    }

    public void exchange() {
        if(from != null && to != null) {
            presenter.getExchangeRate(from, to);
        }
    }

    public void swapCurrencies() {
        String temp = from;
        from = to;
        to = temp;
        setResultText();
    }

    @Override
    public void setExchangeRate(BigDecimal d) {
        rate = d;
        setResultText();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exchange_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.exchangeRefresh:
                exchange();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
