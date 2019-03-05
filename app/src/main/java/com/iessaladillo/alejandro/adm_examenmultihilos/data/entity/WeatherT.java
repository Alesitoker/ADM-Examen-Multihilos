package com.iessaladillo.alejandro.adm_examenmultihilos.data.entity;

public class WeatherT {

    String country;
    String description;
    String icon;
    String tempMax;
    String tempMedia;
    String tempMin;
    String rain;
    String humidity;
    String windSpeed;
    String windDeg;
    String clouds;
    String sunrise;
    String sunset;

    public WeatherT(String country, String description, String icon, String tempMax,
                    String tempMedia, String tempMin, String rain,
                    String humidity, String windSpeed, String windDeg,
                    String clouds, String sunrise, String sunset) {
        this.country = country;
        this.description = description;
        this.icon = icon;
        this.tempMax = tempMax;
        this.tempMedia = tempMedia;
        this.tempMin = tempMin;
        this.rain = rain;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.clouds = clouds;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMedia() {
        return tempMedia;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getRain() {
        return rain;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public String getClouds() {
        return clouds;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
