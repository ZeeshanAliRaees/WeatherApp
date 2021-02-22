package com.app.weather.network

import com.app.weather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private const val BaseUrl = "https://api.openweathermap.org/"
    private val logging = HttpLoggingInterceptor()
    private const val strDebug = "debug"

    private val retrofitClient: Retrofit.Builder by lazy {
        val levelType: HttpLoggingInterceptor.Level

        if (BuildConfig.BUILD_TYPE.contentEquals(strDebug)){
            levelType = HttpLoggingInterceptor.Level.BODY
        }
        else {
            levelType = HttpLoggingInterceptor.Level.NONE
        }

        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}
