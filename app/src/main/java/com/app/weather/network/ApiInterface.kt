package com.app.weather.network

import com.app.weather.models.ForcastResponse
 import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(EndPoints.FORCAST_API)
    suspend fun getForcastByCity(@Query(value="q") city:String, @Query(value="appid") appid: String?="65d00499677e59496ca2f318eb68c049") : Response<ForcastResponse>

}