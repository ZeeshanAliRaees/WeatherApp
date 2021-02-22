package com.app.weather.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.weather.room.RoomConstants

@Entity(tableName = RoomConstants.FORCAST_TABLE)
 class ForcastModel(
    @PrimaryKey
    val dt: Int,
    val dt_txt: String,
    val main: MainModel,
    var weather: List<WeatherModel?>?,
    val wind: WindModel
)
