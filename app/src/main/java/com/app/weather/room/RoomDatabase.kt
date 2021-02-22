package com.example.weather.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.weather.models.ForcastModel
import com.app.weather.room.typeconverters.ListTypeConverters
import com.app.weather.room.typeconverters.MainTypeConverters
import com.app.weather.room.typeconverters.WindTypeConverters

@Database(entities = [ForcastModel::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverters::class,MainTypeConverters::class,WindTypeConverters::class)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun daoAccess(): RoomDao?

}