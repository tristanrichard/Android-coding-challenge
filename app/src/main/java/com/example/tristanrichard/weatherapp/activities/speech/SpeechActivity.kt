package com.example.tristanrichard.weatherapp.activities.speech

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import com.example.tristanrichard.weatherapp.R
import com.example.tristanrichard.weatherapp.activities.extensions.getCapitalisedWordsInString
import com.example.tristanrichard.weatherapp.activities.speech.adapter.PlaceAdapter
import com.example.tristanrichard.weatherapp.activities.weather.WeatherActivity
import com.example.tristanrichard.weatherapp.models.googleplace.PlaceResult
import com.example.tristanrichard.weatherapp.models.googleplace.Prediction
import com.example.tristanrichard.weatherapp.services.places.PlacesService
import com.example.tristanrichard.weatherapp.services.speech.SpeechService
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_speech.*
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class SpeechActivity : AppCompatActivity() {

    var predictions: List<Prediction> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech)

        RxView.clicks(btnSpeak)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    SpeechService.listen(this@SpeechActivity)
                }, {
                    when (it) {
                        is ActivityNotFoundException -> {
                            // TODO Notify user that speech service is unavailable
                        }
                        else -> {
                            // TODO Notify user that an error occurred
                        }
                    }

                })
    }

    private fun processCapturedSpeech(result: ArrayList<String>?) {
        result?.firstOrNull()?.let {
            it.getCapitalisedWordsInString().lastOrNull()?.let { word ->
                PlacesService.searchGooglePlaceWith(word)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            // TODO: Show Progress
                        }
                        .subscribe({
                            handleGooglePlaceResult(it)
                        }, {
                            when (it) {
                                is HttpException -> {
                                    // TODO: Handle server error
                                }
                                is UnknownHostException -> {
                                    // TODO: Handle network error (No internet)
                                }
                            }
                        })
            }
        }
    }

    private fun handleGooglePlaceResult(result: PlaceResult) {

        when (result.predictions.size) {
            0 -> {
                // TODO: Show empty place result
            }
            1 -> {
                launchWeatherActivity(predictions.first().description)
            }
            else -> {
                loadGooglePlayResultInListView(result)
            }

        }

    }

    private fun loadGooglePlayResultInListView(result: PlaceResult) {
        predictions = result.predictions
        val adapter = PlaceAdapter(this, result.predictions, R.layout.activity_speech_place_list_item)
        placeResultListView.adapter = adapter
        placeResultListView.setOnItemClickListener { _, _, position, _ ->
            launchWeatherActivity(predictions[position].description)
        }
    }

    private fun launchWeatherActivity(city: String) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(WeatherActivity.SEARCH_KEY, city)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            SpeechService.REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null && data.hasExtra(RecognizerIntent.EXTRA_RESULTS)) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    processCapturedSpeech(result)
                } else {
                    // TODO: notify user that SpeechRecognizer did not return any data
                }
            }
            else -> {
                // Do nothing
            }
        }
    }


}
