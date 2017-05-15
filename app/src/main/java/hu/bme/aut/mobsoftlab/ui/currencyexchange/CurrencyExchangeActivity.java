package hu.bme.aut.mobsoftlab.ui.currencyexchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.R;
import hu.bme.aut.mobsoftlab.mock.MockCurrencies;

public class CurrencyExchangeActivity extends AppCompatActivity implements CurrencyExchangeScreen {
    @Inject
    protected CurrencyExchangePresenter presenter;

    String from;
    String to;
    double value;
    double rate;

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
                resultText.setText(from);
            }
        });

        targetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                to = targetAdapter.getItem(position);
                setResultText();
            }
        });

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapCurrencies();
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
                } catch(NumberFormatException e) { }
                setResultText();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setResultText() {
        resultText.setText(value + " " + from + " = " + (rate * value) + to);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachScreen();
    }

    @Override
    public void exchange() {
        presenter.getExchangeRate(from, to);
    }

    @Override
    public void swapCurrencies() {
        String temp = from;
        from = to;
        to = temp;
        setResultText();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
