package com.app.weather.models

data class ForcastResponse(
    val cod: String,
    val message: String,
    val list: List<ForcastModel>,
)
