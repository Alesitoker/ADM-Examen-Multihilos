package com.iessaladillo.alejandro.adm_examenmultihilos.data.mapper;

import android.util.Log;

import com.iessaladillo.alejandro.adm_examenmultihilos.data.entity.WeatherT;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.Clouds;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.Main;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.Rain;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.Sys;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.Weather;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.WeatherResponse;
import com.iessaladillo.alejandro.adm_examenmultihilos.data.remote.dto.Wind;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherMapper {

    public WeatherT map(WeatherResponse weatherResponse) {
        final String VACIO = "-";
        String country = VACIO, icon = VACIO, description = VACIO, tempMin = VACIO,
                tempMax = VACIO, tempMedia = VACIO, rain = VACIO, humidity = VACIO,
                windSpeed = VACIO, windDeg = VACIO, clouds = VACIO, sunrise = VACIO, sunset = VACIO;
        Calendar calendar = Calendar.getInstance();
        Main main;
        Sys sys;
        Clouds cloudsO;
        Rain rainO;
        Wind wind;
        Weather weather;
        String pattern = "HH:mm";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        if (weatherResponse.getMain() != null) {
            main = weatherResponse.getMain();

            tempMedia = String.valueOf(main.getTemp());
            tempMax = String.valueOf(main.getTempMax());
            tempMin = String.valueOf(main.getTempMin());
            humidity = String.valueOf(main.getHumidity());
        }

        if (weatherResponse.getWeather() != null) {
            Log.d("agua", weatherResponse.getWeather().get(0).getIcon());
            weather = weatherResponse.getWeather().get(0);
            description = weather.getDescription();
            icon = String.format("http://openweathermap.org/img/w/%s.png", weather.getIcon());
        }

        if (weatherResponse.getName() != null && weatherResponse.getSys() != null) {
            sys = weatherResponse.getSys();
            country = String.format("%s, %s", weatherResponse.getName(), sys.getCountry());
            calendar.setTimeInMillis(sys.getSunset());
            sunset = format.format(calendar.getTime());
            calendar.setTimeInMillis(sys.getSunrise());
            sunrise = format.format(calendar.getTime());
        }

        if (weatherResponse.getClouds() != null) {
            cloudsO = weatherResponse.getClouds();
            clouds = String.valueOf(cloudsO.getAll());
        }

        if (weatherResponse.getRain() != null) {
            rainO = weatherResponse.getRain();
            if (rainO.get1h() != null) {
                rain = String.valueOf(rainO.get1h());
            } else {
                rain = String.valueOf(rainO.get3h());
            }
        }

        if (weatherResponse.getWind() != null) {
            wind = weatherResponse.getWind();

            windSpeed = String.valueOf(wind.getSpeed());
            windDeg = String.valueOf(wind.getDeg());
        }

        return new WeatherT(country, description, icon,tempMax, tempMedia, tempMin, rain, humidity, windSpeed,
                windDeg, clouds, sunrise, sunset);
    }
}
