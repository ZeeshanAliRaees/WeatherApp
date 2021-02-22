package com.app.weather.network
import com.app.weather.models.ForcastResponse
import com.example.weather.network.ApiInterface

class RemoteDataSource(private val apiService: ApiInterface): BaseDataSource() {
    suspend fun getForcastResult(cityName:String,appId:String): Resource<ForcastResponse> {

    return getResult {apiService.getForcastByCity(cityName,appId)}

    }
 }