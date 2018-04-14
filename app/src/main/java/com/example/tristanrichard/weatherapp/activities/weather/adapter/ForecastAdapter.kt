package com.example.tristanrichard.weatherapp.activities.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.tristanrichard.weatherapp.activities.extensions.getSimpleDate
import com.example.tristanrichard.weatherapp.models.weather.WeatherResult
import kotlinx.android.synthetic.main.fragment_forecast_list_item.view.*

class ForecastAdapter(val context: Context?, val data: List<WeatherResult>, val itemRes: Int): BaseAdapter() {
    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView = _convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(itemRes, null)
        }

        val data = getItem(position)

        convertView?.let {
            it.datePlaceholder?.text = "${data.dt_txt.getSimpleDate()} \t Wind: ${data.wind.speed} m/s" // TODO: Locolise
            it.minTempPlaceholder?.text = "${data.main.temp_min} C"
            it.tempPlaceholder?.text = "${data.main.temp} C"
            it.maxTempPlaceholder?.text = "${data.main.temp_max} C"
        }

        return convertView!!
    }

    override fun getItem(position: Int): WeatherResult {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return data.size
    }
}