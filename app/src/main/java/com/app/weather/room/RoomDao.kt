package com.example.weather.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.weather.models.ForcastModel

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForcastResult(items: List<ForcastModel>)

    @Query("SELECT * FROM " + RoomConstants.FORCAST_TABLE)
    fun getForcastResult(): LiveData<List<ForcastModel>>

}