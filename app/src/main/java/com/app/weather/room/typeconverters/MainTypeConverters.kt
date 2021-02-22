package com.app.weather.room.typeconverters

import androidx.room.TypeConverter
import com.app.weather.models.MainModel
import com.google.gson.Gson

class MainTypeConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun stringToMainObject(data: String?): MainModel {
            if (data == null) {
                return MainModel()
            }
            return  Gson().fromJson(data,MainModel::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun objectToMainString(mainObj: MainModel): String {
            return  Gson().toJson(mainObj)
        }
    }
}