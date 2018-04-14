package com.example.tristanrichard.weatherapp.activities.weather.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.tristanrichard.weatherapp.R
import com.example.tristanrichard.weatherapp.activities.weather.fragments.ForecastFragment
import com.example.tristanrichard.weatherapp.activities.weather.fragments.WeatherFragment

class WeatherViewPagerAdapter(private val mContext: Context, private val city: String, private val fm: FragmentManager) : FragmentStatePagerAdapter(fm)  {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WeatherFragment.newInstance(city)
            }
            else -> {
                ForecastFragment.newInstance(city)
            }
        }
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (count == 1) {
            return null
        }

        return if (position == 0) {
            mContext.getString(R.string.weather_activity_view_pager_weather)
        } else {
            mContext.getString(R.string.weather_activity_view_pager_forecast)
        }
    }

}