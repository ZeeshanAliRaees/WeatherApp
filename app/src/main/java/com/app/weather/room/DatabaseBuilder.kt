package com.example.weather.room

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    private var INSTANCE: RoomDatabase? = null

    fun getInstance(context: Context): RoomDatabase {
        if (INSTANCE == null) {
            synchronized(RoomDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            RoomDatabase::class.java,
            RoomConstants.DB_NAME
        ).build()

}