package com.example.tristanrichard.weatherapp.services.places.api

import com.example.tristanrichard.weatherapp.models.googleplace.PlaceResult
import com.example.tristanrichard.weatherapp.services.places.PlacesService
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoCompleteApi {

    @GET("autocomplete/json?key=${PlacesService.API_KEY}")
    fun searchForMatchingCities(@Query("input") name: String): Observable<PlaceResult>
}