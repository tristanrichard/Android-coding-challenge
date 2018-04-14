package com.example.tristanrichard.weatherapp.services.weather.api

import com.example.tristanrichard.weatherapp.services.weather.WeatherService
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ForecastApi {

    @Headers(WeatherService.API_KEY_HEADER)
    @GET("forecast")
    fun getWeatherForCityWithName(@Query("q") name: String, @Query("units") units: String, @Query("lang") lang: String): Observable<ResponseBody>

}