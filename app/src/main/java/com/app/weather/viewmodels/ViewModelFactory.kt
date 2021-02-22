package com.app.weather.viewmodels

 import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.weather.repository.MainRepository


class ViewModelFactory(private val repo: MainRepository) : ViewModelProvider.Factory {
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherListViewModel(repo) as T
    }
}