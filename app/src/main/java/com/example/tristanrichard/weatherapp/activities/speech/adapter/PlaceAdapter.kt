package com.example.tristanrichard.weatherapp.activities.speech.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.tristanrichard.weatherapp.models.googleplace.Prediction
import kotlinx.android.synthetic.main.activity_speech_place_list_item.view.*

class PlaceAdapter(val context: Context, val data: List<Prediction>, val itemRes: Int): BaseAdapter() {
    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView = _convertView

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(itemRes, null)
        }

        val data = getItem(position)

        convertView?.let {
            it.placeName?.text = data.description
        }

        return convertView!!
    }

    override fun getItem(position: Int): Prediction {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return data.size
    }
}