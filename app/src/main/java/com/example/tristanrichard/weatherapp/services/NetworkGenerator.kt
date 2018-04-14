package com.example.tristanrichard.weatherapp.services

import com.example.tristanrichard.weatherapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkGenerator {

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

    internal fun getretrofit(baseURL: String): Retrofit {
        if (retrofit == null) {
            // Build Retrofit object
            retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient())
                    .build()
        }

        return retrofit!!
    }
}