package com.example.tristanrichard.weatherapp.activities.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tristanrichard.weatherapp.R
import com.example.tristanrichard.weatherapp.activities.weather.adapter.WeatherViewPagerAdapter
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val city = intent.getStringExtra(SEARCH_KEY)

        val viewPagerAdapter = WeatherViewPagerAdapter(this, city, supportFragmentManager)
        weatherViewPager?.let {
            it.adapter = viewPagerAdapter
            fragmentTabs?.setupWithViewPager(it)
        }


    }

    companion object {
        const val SEARCH_KEY = "city_description"
    }
}
