package com.example.tristanrichard.weatherapp

import com.example.tristanrichard.weatherapp.activities.extensions.getCapitalisedWordsInString
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StringTests {
    @Test
    fun findCapitalisedWordsInString() {

        var testString = "it is launch time"
        var result = testString.getCapitalisedWordsInString()
        assertEquals(0, result.size)

        testString = "it is launch time in Copenhagen"
        result = testString.getCapitalisedWordsInString()
        assertEquals(1, result.size)
        assertEquals("Copenhagen", result.first())

        testString = "it is launch time in Copenhagen Denmark"
        result = testString.getCapitalisedWordsInString()
        assertEquals(2, testString.getCapitalisedWordsInString().size)
        assertEquals("Copenhagen", result.first())
        assertEquals("Denmark", result.last())
    }
}
