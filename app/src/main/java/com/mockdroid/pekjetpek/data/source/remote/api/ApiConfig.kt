package com.mockdroid.pekjetpek.data.source.remote.api

import com.mockdroid.pekjetpek.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val httpClient = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(logging)
            }

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_TMDB)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}