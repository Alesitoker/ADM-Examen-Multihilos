package com.iessaladillo.alejandro.adm_examenmultihilos.data.remote;

import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Call<WeatherResponse> weather(@Query("appid") String key, @Query("q") String localidad,
                                  @Query("units") String unidad, @Query("lang") String idioma);
}
