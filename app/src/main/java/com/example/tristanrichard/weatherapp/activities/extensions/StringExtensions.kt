package com.example.tristanrichard.weatherapp.activities.extensions

fun String.getCapitalisedWordsInString() : List<String> {
//    return Regex.fromLiteral("([A-Z][a-z']*)(\\s[A-Z][a-z']*)*").split(this)
//    return Regex("\"((\\b[^\\s]+\\b)((?<=\\.\\w).)?)").split(this)
    return this.split(" ").filter { it.matches("[A-Z][^A-Z]*$".toRegex()) }
}