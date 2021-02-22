package com.example.weather.viewmodel
import android.content.Context
import androidx.lifecycle.*
import com.app.weather.models.ForcastModel
import com.example.weather.repository.MainRepository
import com.example.weather.util.Tools
import kotlinx.coroutines.*

class WeatherListViewModel(val repository: MainRepository, val context: Context) : ViewModel(){
    fun getWeatherList(city_name:String) = repository.getForcast(context,city_name)
 }
