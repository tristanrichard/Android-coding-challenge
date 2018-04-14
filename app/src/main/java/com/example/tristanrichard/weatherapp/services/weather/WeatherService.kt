package com.example.tristanrichard.weatherapp.services.weather

import com.example.tristanrichard.weatherapp.enums.WeatherUnit
import com.example.tristanrichard.weatherapp.models.weather.ForecastResult
import com.example.tristanrichard.weatherapp.models.weather.WeatherResult
import com.example.tristanrichard.weatherapp.services.NetworkGenerator
import com.example.tristanrichard.weatherapp.services.weather.api.ForecastApi
import com.example.tristanrichard.weatherapp.services.weather.api.WeatherApi
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*

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

    fun getWeatherForCityWithName(name: String): Observable<WeatherResult> {
        return createService(WeatherApi::class.java).getWeatherForCityWithName(name, WeatherUnit.Metric.name.toLowerCase(), Locale.getDefault().country)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    // TODO: Store search result to minimise network requests
                }
    }

    fun getForecastForCityWithName(name: String): Observable<ForecastResult> {
        return createService(ForecastApi::class.java).getForecastForCityWithName(name, WeatherUnit.Metric.name.toLowerCase(), Locale.getDefault().country)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    // TODO: Store search result to minimise network requests
                }
    }
}