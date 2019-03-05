package com.iessaladillo.alejandro.adm_examenmultihilos.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiImpl {

    private static WeatherApiImpl instance;
    private final WeatherApi API;

    private WeatherApiImpl(WeatherApi api) {
        API = api;
    }

    public static WeatherApiImpl getInstance() {
        if (instance == null) {
            instance = new WeatherApiImpl(buildInstance());
        }
        return instance;
    }

    private static WeatherApi buildInstance() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.openweathermap.org/data/2.5/").
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(WeatherApi.class);
    }

    public WeatherApi getAPI() {
        return API;
    }

}
