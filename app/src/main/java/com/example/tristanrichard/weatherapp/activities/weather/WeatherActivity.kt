package com.example.tristanrichard.weatherapp.activities.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tristanrichard.weatherapp.R

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val city = intent.getStringExtra(SEARCH_KEY)


    }

    companion object {
        const val SEARCH_KEY = "city_description"
    }
}
