package com.example.tristanrichard.weatherapp.activities.weather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tristanrichard.weatherapp.R
import com.example.tristanrichard.weatherapp.services.weather.WeatherService
import io.reactivex.android.schedulers.AndroidSchedulers

class ForecastFragment : Fragment() {
    private var city: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(ARG_PARAM1)
        }

        city?.let {
            WeatherService.getForecastForCityWithName(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        // TODO: Show Progress
                    }
                    .subscribe({
                        // TODO: Show result
                        Log.i(this@ForecastFragment.javaClass.simpleName, it.list.size.toString())
                    }, {
                        // TODO: Handle errors
                        Log.e(this@ForecastFragment.javaClass.simpleName, it.message)
                    })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }


    companion object {

        private const val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String) =
                ForecastFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}
