package com.app.weather.room.typeconverters

import androidx.room.TypeConverter
import com.app.weather.models.WindModel
import com.app.weather.models.WeatherModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WindTypeConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun stringToMainObject(data: String?): WindModel {
            if (data == null) {
                return WindModel()
            }
            return  Gson().fromJson(data,WindModel::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun objectToMainString(mainObj: WindModel): String {
            return  Gson().toJson(mainObj)
        }
    }
}