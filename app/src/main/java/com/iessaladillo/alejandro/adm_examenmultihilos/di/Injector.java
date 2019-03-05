package com.iessaladillo.alejandro.adm_examenmultihilos.di;

import com.iessaladillo.alejandro.adm_examenmultihilos.data.Repository;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.mapper.WeatherMapper;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.WeatherApiImpl;
import com.iessaladillo.alejandro.adm_examenmultihilos.ui.main.MainFragmentViewModelFactory;

public class Injector {

    private Injector(){}

    public static MainFragmentViewModelFactory provideMainFragmentViewModelFactory() {
        return new MainFragmentViewModelFactory(getRepository());
    }

    private static Repository getRepository() {
        return RepositoryImpl.getInstance(getWeatherApi(), provideWeatherMapper());
    }

    private static WeatherApiImpl getWeatherApi() {
        return WeatherApiImpl.getInstance();
    }

    private static WeatherMapper provideWeatherMapper() {
        return new WeatherMapper();
    }
}
