package com.iessaladillo.alejandro.adm_examenmultihilos.ui.main;

import android.app.Notification;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iessaladillo.alejandro.adm_examenmultihilos.R;
import com.iessaladillo.alejandro.adm_examenmultihilos.base.EventObserver;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.entity.WeatherT;
import com.iessaladillo.alejandro.adm_examenmultihilos.databinding.FragmentMainBinding;
import com.iessaladillo.alejandro.adm_examenmultihilos.di.Injector;
import com.squareup.picasso.Picasso;

import static com.iessaladillo.alejandro.adm_examenmultihilos.Constants.CHANNEL_ID;

public class MainFragment extends Fragment {

    private final int NOTIFICATION_ID = 1;
    private MainFragmentViewModel viewModel;
    private FragmentMainBinding b;
    private NotificationManagerCompat notificationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentMainBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this,
                Injector.provideMainFragmentViewModelFactory()).get(MainFragmentViewModel.class);
        setupViews();
        observe();
    }

    private void observe() {
        viewModel.getError().observe(this, new EventObserver<>(message -> showError(message)));
        viewModel.getWeather().observe(this, weatherT -> showWeather(weatherT));
    }

    private void showWeather(WeatherT weatherT) {
        notificar(weatherT.getCountry(), weatherT.getDescription());
        b.lblCountry.setText(weatherT.getCountry());
        b.lblDescription.setText(weatherT.getDescription());
        Picasso.with(requireContext()).load(weatherT.getIcon()).into(b.imgIcon);
        b.lblTempMax.setText(getString(R.string.lblTempMax, weatherT.getTempMax()));
        b.lblTempMin.setText(getString(R.string.lblTempMin, weatherT.getTempMin()));
        b.lblTempMedia.setText(getString(R.string.lblTempMedia, weatherT.getTempMedia()));
        b.lblRain.setText(getString(R.string.lblRain, weatherT.getRain()));
        b.lblHumidity.setText(getString(R.string.lblHumidity_data, weatherT.getHumidity()));
        b.lblWindSpeed.setText(getString(R.string.lblWindSpeed, weatherT.getWindSpeed()));
        b.lblWindDeg.setText(getString(R.string.lblWindDeg, weatherT.getWindDeg()));
        b.lblClouds.setText(getString(R.string.lblClouds_data, weatherT.getClouds()));
        b.lblSunrise.setText(getString(R.string.lblSunrise, weatherT.getSunrise()));
        b.lblSunset.setText(getString(R.string.lblSunset, weatherT.getSunset()));
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setupViews() {
        b.fab.setOnClickListener(v -> searchWeather());

        b.lblRain.setText(getString(R.string.lblRain, ""));
        b.lblSunrise.setText(getString(R.string.lblSunrise, ""));
        b.lblSunset.setText(getString(R.string.lblSunset, ""));

        notificationManager = NotificationManagerCompat.from(requireContext());

    }

    private void searchWeather() {
        viewModel.searchWeather(b.txtCiudad.getText().toString());
    }

    private void notificar(String country, String description) {
        Notification notification = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(country)
                .setContentText(description)
                .build();

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
