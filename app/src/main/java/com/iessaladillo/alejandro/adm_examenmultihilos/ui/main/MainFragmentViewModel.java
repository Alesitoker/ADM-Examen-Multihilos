package com.iessaladillo.alejandro.adm_examenmultihilos.ui.main;

import android.util.Log;

import com.iessaladillo.alejandro.adm_examenmultihilos.base.Event;
import com.iessaladillo.alejandro.adm_examenmultihilos.base.Resource;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.Repository;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.entity.WeatherT;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MainFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> searchTrigger = new MutableLiveData<>();
    private final LiveData<Boolean> loading;
    private final LiveData<Resource<WeatherT>> searchWeather;
    private final MediatorLiveData<Event<String>> error = new MediatorLiveData<>();
    private final MediatorLiveData<WeatherT> weather = new MediatorLiveData<>();
    private final Repository repository;

    public MainFragmentViewModel(Repository repository) {
        this.repository = repository;

        searchWeather = Transformations.switchMap(searchTrigger,
                weather -> repository.searchWeather(weather));
        loading = Transformations.map(searchWeather, resource -> resource.isLoading());
        error.addSource(searchWeather, resource -> {
            if (resource.hasError()) {
                error.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
        weather.addSource(searchWeather, resource -> {
            if (resource.hasSuccess()) {
                weather.setValue(resource.getData());
            }
        });


    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Event<String>> getError() {
        return error;
    }

    public LiveData<WeatherT> getWeather() {
        return weather;
    }

    public void searchWeather(String localidad) {
        if (!localidad.isEmpty() && !localidad.matches("[ ]*")) {
            searchTrigger.setValue(localidad);
        }

    }
}
