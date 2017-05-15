package hu.bme.aut.mobsoftlab.ui.newfavorite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.R;
import hu.bme.aut.mobsoftlab.mock.MockCurrencies;
import hu.bme.aut.mobsoftlab.mock.interceptors.CurrencyMock;

public class NewFavoriteActivity extends AppCompatActivity implements NewFavoriteScreen {
    @Inject
    protected NewFavoritePresenter presenter;

    String from;
    String to;

    TextView fromText;
    TextView toText;
    Button swapButton;
    Button saveButton;
    ListView sourceListView;
    ArrayAdapter<String> sourceAdapter;
    ListView targetListView;
    ArrayAdapter<String> targetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        MobSoftApplication.injector.inject(this);

        fromText = (TextView) findViewById(R.id.newFavoriteFromText);
        toText = (TextView) findViewById(R.id.newFavoriteToText);
        sourceListView = (ListView) findViewById(R.id.newFavoriteSourceList);
        targetListView = (ListView) findViewById(R.id.newFavoriteTargetList);
        swapButton = (Button) findViewById(R.id.newFavoriteSwapButton);
        saveButton = (Button) findViewById(R.id.newFavoriteSaveButton);

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
                fromText.setText(from);
            }
        });

        targetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                to = targetAdapter.getItem(position);
                toText.setText(to);
            }
        });

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapCurrencies();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
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

    public void save() {
        if(from != null && to != null && !from.equals(to)) {
            presenter.save(from ,to);
        }
    }

    public void swapCurrencies() {
        String temp = from;
        from = to;
        to = temp;
        fromText.setText(from);
        toText.setText(to);
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
