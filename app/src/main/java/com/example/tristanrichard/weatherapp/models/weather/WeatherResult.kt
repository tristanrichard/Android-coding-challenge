package com.example.tristanrichard.weatherapp.models.weather

class WeatherResult(
        val coord: Coord,
        val weather: ArrayList<Weather>,
        val main: Main,
        val visibility: Int,
        val wind: Wind,
        val name: String,
        val sys: Sys
)