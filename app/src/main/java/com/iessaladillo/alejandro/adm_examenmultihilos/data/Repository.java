package com.iessaladillo.alejandro.adm_examenmultihilos.data;

import com.iessaladillo.alejandro.adm_examenmultihilos.base.Resource;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.entity.WeatherT;

import androidx.lifecycle.LiveData;

public interface Repository {

    LiveData<Resource<WeatherT>> searchWeather(String localidad);
}
