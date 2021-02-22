package com.app.weather.listeners

import com.app.weather.models.ForcastModel

interface WeatherClickListener {
    fun onWeatherClick( forcastModel: ForcastModel)

}