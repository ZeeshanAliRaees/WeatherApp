package com.app.weather.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.repository.MainRepository
import com.example.weather.viewmodel.WeatherListViewModel

class ViewModelFactory(val repo: MainRepository, val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherListViewModel(repo,context) as T
    }
}