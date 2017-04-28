package hu.bme.aut.mobsoftlab.ui.newfavorite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.MobSoftApplication;
import hu.bme.aut.mobsoftlab.R;

public class NewFavoriteActivity extends AppCompatActivity implements NewFavoriteScreen {
    @Inject
    protected NewFavoritePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSoftApplication.injector.inject(this);
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
    public void save() {
        presenter.save();
    }

    @Override
    public void swapCurrencies() {
        // TODO swap from and to
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
