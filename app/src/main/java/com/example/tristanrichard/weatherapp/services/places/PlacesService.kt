package com.example.tristanrichard.weatherapp.services.places

import com.example.tristanrichard.weatherapp.services.NetworkGenerator

object PlacesService {

    private var instance: NetworkGenerator? = null
    const val API_URL = "https://maps.googleapis.com/maps/api/place/"
    const val API_KEY = "AIzaSyC49O53N6OHrXP9wRj60VkpqueyUIkkFfs"

    fun <S> createService(serviceClass: Class<S>): S {
        if (instance == null) {
            instance = NetworkGenerator()
        }
        return instance!!.getretrofit(API_URL).create(serviceClass)
    }

}