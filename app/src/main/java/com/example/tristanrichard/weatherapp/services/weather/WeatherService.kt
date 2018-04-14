package com.example.tristanrichard.weatherapp.services.weather

import com.example.tristanrichard.weatherapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    private var httpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    private fun getHttpClient(): OkHttpClient {
        if (httpClient == null) {

            // Set Logging interceptor
            val log = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                log.level = HttpLoggingInterceptor.Level.HEADERS
            } else {
                log.level = HttpLoggingInterceptor.Level.NONE
            }

            // Create Httpclient
            val builder = OkHttpClient.Builder()
                    .addInterceptor(log)
                    .connectionPool(ConnectionPool())


            httpClient = builder.build()
        }
        return httpClient!!

    }

    internal fun getretrofit(): Retrofit {
        if (retrofit == null) {
            // Build Retrofit object
            retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient())
                    .build()
        }

        return retrofit!!
    }

    companion object {

        private var instance: WeatherService? = null
        const val API_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY_HEADER = "x-api-key: 2650079ad9431100673234e437315de2"

        fun <S> createService(serviceClass: Class<S>): S {
            if (instance == null) {
                instance = WeatherService()
            }
            return instance!!.getretrofit().create(serviceClass)
        }
    }
}