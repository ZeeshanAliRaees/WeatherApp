package com.app.weather.room.typeconverters

import androidx.room.TypeConverter
import com.app.weather.models.WeatherModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun stringToSomeObjectList(data: String?): List<WeatherModel> {
            if (data == null) {
                return emptyList()
            }
            val listType = object : TypeToken<List<WeatherModel?>?>() {}.type
            return  Gson().fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<WeatherModel?>?): String {
            return  Gson().toJson(someObjects)
        }
    }
}