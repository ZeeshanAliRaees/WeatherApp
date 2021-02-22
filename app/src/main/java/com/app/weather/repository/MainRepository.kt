package com.example.weather.repository

import android.content.Context
import com.app.weather.R
import com.app.weather.network.BaseDataSource
import com.app.weather.network.RemoteDataSource
import com.app.weather.util.loadRepositoryData
 import com.example.weather.room.RoomDao

class MainRepository(private val remoteDataSource: RemoteDataSource, private val forcastDao: RoomDao): BaseDataSource() {
    fun getForcast(context: Context?, city_name:String) = loadRepositoryData(
            databaseQuery = {forcastDao.getForcastResult()},
            networkCall = { remoteDataSource.getForcastResult(city_name,context?.resources?.getString(
                    R.string.api_key)!!)},
            saveCallResult = { forcastDao.insertForcastResult(it.list)}
    )
}