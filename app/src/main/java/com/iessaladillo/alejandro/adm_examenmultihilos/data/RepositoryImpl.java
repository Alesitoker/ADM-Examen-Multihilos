package com.iessaladillo.alejandro.adm_examenmultihilos.data;

import android.util.Log;

import com.iessaladillo.alejandro.adm_examenmultihilos.base.Resource;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.entity.WeatherT;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.mapper.WeatherMapper;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.WeatherApiImpl;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.WeatherResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.Constants.*;

public class RepositoryImpl implements Repository {

    private MutableLiveData<Resource<WeatherT>> result = new MutableLiveData<>();
    private static RepositoryImpl instance;
    private final WeatherApiImpl weatherApi;
    private final WeatherMapper weatherMapper;

    private RepositoryImpl(WeatherApiImpl weatherApi, WeatherMapper weatherMapper){
        this.weatherApi = weatherApi;
        this.weatherMapper = weatherMapper;
    }

    public static RepositoryImpl getInstance(WeatherApiImpl weatherApi, WeatherMapper weatherMapper) {
        if (instance == null) {
            instance = new RepositoryImpl(weatherApi, weatherMapper);
        }
        return instance;
    }
    @Override
    public LiveData<Resource<WeatherT>> searchWeather(String localidad) {
        result.setValue(Resource.loading());
        Call<WeatherResponse> call = weatherApi.getAPI().weather(appid, localidad, units, lang);
        call.enqueue(new Callback<WeatherResponse>() {
            String message;
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    result.postValue(Resource.success(weatherMapper.map(response.body())));
                } else {
                    if (response.message().isEmpty()) {
                        message = "Error inesperado";
                    } else {
                        message = response.message();
                    }
                    result.postValue(Resource.error(new Exception(message)));
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                result.postValue(Resource.error(new Exception(t.getMessage())));
            }
        });
        return result;
    }
}
