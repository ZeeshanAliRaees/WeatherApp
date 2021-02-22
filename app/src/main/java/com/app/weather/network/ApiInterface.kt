package com.example.weather.network

import androidx.lifecycle.LiveData
import com.app.weather.models.ForcastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(EndPoints.FORCAST_API)
    suspend fun getForcastByCity(@Query(value="q") city:String, @Query(value="appid") appid: String?) : Response<ForcastResponse>

}