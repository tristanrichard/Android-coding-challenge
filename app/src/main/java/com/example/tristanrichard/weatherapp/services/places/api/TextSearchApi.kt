package com.example.tristanrichard.weatherapp.services.places.api

import com.example.tristanrichard.weatherapp.services.places.PlacesService
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface TextSearchApi {

    @GET("autocomplete/json?key=${PlacesService.API_KEY}")
    fun searchForMatchingCities(@Query("input") name: String): Observable<PlaceResult>
}