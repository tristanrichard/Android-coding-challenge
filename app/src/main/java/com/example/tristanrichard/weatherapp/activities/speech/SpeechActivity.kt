package com.example.tristanrichard.weatherapp.activities.speech

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import com.example.tristanrichard.weatherapp.R
import com.example.tristanrichard.weatherapp.activities.extensions.getCapitalisedWordsInString
import com.example.tristanrichard.weatherapp.services.speech.SpeechService
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_speech.*
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class SpeechActivity : AppCompatActivity() {

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
                            // TODO Notify user that an error occured
                        }
                    }

                })
    }

    private fun processCapturedSpeech(result: ArrayList<String>?) {
        result?.forEach {
            val words = it.getCapitalisedWordsInString()
            Log.i(this@SpeechActivity.localClassName, words.toString())
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            SpeechService.REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null && data.hasExtra(RecognizerIntent.EXTRA_RESULTS)) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    processCapturedSpeech(result)
                }
            }
            else -> {
                // Do nothing
            }
        }
    }


}
