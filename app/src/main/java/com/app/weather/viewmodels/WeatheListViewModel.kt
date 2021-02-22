package com.app.weather.viewmodels
 import androidx.lifecycle.*
import com.app.weather.models.ForcastModel
import com.app.weather.repository.MainRepository

class WeatherListViewModel(private val repository: MainRepository) : ViewModel(){
    lateinit var detailItem:ForcastModel
    var cityName:String=""
    fun getWeatherList() = repository.getForcast(cityName)
 }
