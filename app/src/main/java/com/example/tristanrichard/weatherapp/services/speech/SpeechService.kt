package com.example.tristanrichard.weatherapp.services.speech

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.Log
import java.util.*

object SpeechService {

    const val REQUEST_CODE = 10

    fun listen(activity: Activity) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        activity.startActivityForResult(intent, REQUEST_CODE)
    }
}