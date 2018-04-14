package com.example.tristanrichard.weatherapp.activities.weather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tristanrichard.weatherapp.R
import com.example.tristanrichard.weatherapp.models.weather.WeatherResult
import com.example.tristanrichard.weatherapp.services.weather.WeatherService
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherFragment : Fragment() {
    private var city: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(ARG_PARAM1)
        }

        city?.let {
            WeatherService.getWeatherForCityWithName(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        progressBar?.visibility = View.VISIBLE
                    }
                    .doFinally {
                        progressBar?.visibility = View.INVISIBLE
                    }
                    .subscribe({
                        setupData(it)

                    }, {
                        // TODO: Handle errors
                        Log.e(this@WeatherFragment.javaClass.simpleName, it.message)
                    })
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    fun setupData(result: WeatherResult) {

        cityPlaceHolder?.text = city
        tempPlaceHolder?.text = "${result.main.temp} C" // TODO: Locolize
        windPlaceholder?.text = "${result.wind.speed} m/s"
        minTempPlaceholder?.text = "min ${result.main.temp_min} C"
        maxTempPLaceholder?.text = "max ${result.main.temp_max} C"

    }

    companion object {

        private const val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String) =
                WeatherFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}
