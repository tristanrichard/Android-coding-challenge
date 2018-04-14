package com.example.tristanrichard.weatherapp.services.weather

import com.example.tristanrichard.weatherapp.services.NetworkGenerator

object WeatherService {

    private var instance: NetworkGenerator? = null
    const val API_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY_HEADER = "x-api-key: 2650079ad9431100673234e437315de2"

    fun <S> createService(serviceClass: Class<S>): S {
        if (instance == null) {
            instance = NetworkGenerator()
        }
        return instance!!.getretrofit(API_URL).create(serviceClass)
    }
}