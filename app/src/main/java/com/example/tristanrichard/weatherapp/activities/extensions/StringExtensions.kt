package com.example.tristanrichard.weatherapp.activities.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.getCapitalisedWordsInString(): List<String> {
    return this.split(" ").filter { it.matches("[A-Z][^A-Z]*$".toRegex()) }
}

fun String.getSimpleDate(): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = dateFormatter.parse(this)

    val printDateFormatter = SimpleDateFormat("d. MMM HH:mm", Locale.getDefault())

    return printDateFormatter.format(date)
}