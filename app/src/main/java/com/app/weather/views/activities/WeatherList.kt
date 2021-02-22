package com.app.weather.views.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.weather.R
import com.app.weather.network.RemoteDataSource
import com.app.weather.network.RetrofitClient
import com.app.weather.util.Constants
import com.app.weather.util.Tools
import com.app.weather.viewmodels.ViewModelFactory
import com.app.weather.repository.MainRepository
import com.app.weather.room.DatabaseBuilder
 import com.app.weather.viewmodels.WeatherListViewModel

class WeatherList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        val txtTitle: TextView =findViewById(R.id.txtTitle)
        val btnBack: ImageButton =findViewById(R.id.btnBack)
        setBackButton(btnBack)

         if(Tools.hasValue(intent.getStringExtra(Constants.KEY_CITY_NAME))) {
            val cityName=intent.getStringExtra(Constants.KEY_CITY_NAME)
            setUpViewModel(cityName)
            txtTitle.text=cityName
         }
     }

    private fun setUpViewModel(city_name: String?) {
        val viewModelFactory= ViewModelFactory(
            MainRepository(
                RemoteDataSource(RetrofitClient.apiInterface),
                DatabaseBuilder.getInstance(this).daoAccess()!!
            )
        )

        val weatherListViewModel= ViewModelProvider(this,viewModelFactory).get(
            WeatherListViewModel::class.java
        )

        weatherListViewModel.cityName=city_name!!
    }
    private fun setBackButton(btnBack: ImageButton) {
        btnBack.setOnClickListener{onBackPressed()}
    }
}