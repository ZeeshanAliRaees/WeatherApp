package com.app.weather.network
import com.app.weather.models.ForcastResponse

class RemoteDataSource(private val apiService: ApiInterface): BaseDataSource() {
    suspend fun getForcastResult(cityName:String): Resource<ForcastResponse> {
    return getResult {apiService.getForcastByCity(cityName)}

    }
 }