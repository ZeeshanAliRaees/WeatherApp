package com.app.weather.repository

import com.app.weather.network.BaseDataSource
import com.app.weather.network.RemoteDataSource
import com.app.weather.room.RoomDao
import com.app.weather.util.loadRepositoryData

class MainRepository(private val remoteDataSource: RemoteDataSource, private val forcastDao: RoomDao): BaseDataSource() {
    fun getForcast(city_name:String) = loadRepositoryData(
            databaseQuery = {forcastDao.getForcastResult()},
            networkCall = { remoteDataSource.getForcastResult(city_name)},
            saveCallResult = { forcastDao.insertForcastResult(it.list)}
    )
}