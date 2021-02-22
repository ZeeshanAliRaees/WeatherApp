package com.app.weather.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weather.R
import com.app.weather.databinding.ActivityWeatherListBinding
import com.app.weather.listeners.WeatherClickListener
import com.app.weather.models.ForcastModel
import com.app.weather.viewmodels.ViewModelFactory
import com.app.weather.adapter.WeatherAdapter
import com.app.weather.network.RemoteDataSource
import com.app.weather.network.Resource
import com.example.weather.network.RetrofitClient
import com.example.weather.repository.MainRepository
import com.example.weather.room.DatabaseBuilder
import com.example.weather.viewmodel.WeatherListViewModel

class WeatherList : AppCompatActivity(),WeatherClickListener {
    var weatherListViewModel: WeatherListViewModel? = null
    var dataBinding:ActivityWeatherListBinding?=null
    var cityName:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        initbinding()
        initViewModel()
        initializeRecyclerview()
        cityName="Manassas Park"
        subscibeWeatherObserver()

    }
    private fun initViewModel() {
        val viewModelFactory= ViewModelFactory(MainRepository(RemoteDataSource( RetrofitClient.apiInterface),
                DatabaseBuilder.getInstance(this@WeatherList).daoAccess()!!),this@WeatherList)
        weatherListViewModel = ViewModelProvider(this@WeatherList,viewModelFactory).get(WeatherListViewModel::class.java)

    }

    private fun initbinding(){
        dataBinding =  DataBindingUtil.setContentView<ActivityWeatherListBinding>(this, R.layout.activity_weather_list)

    }

    private fun initializeRecyclerview(){
        dataBinding?.wetherRecyclerView?.addItemDecoration(
            DividerItemDecoration(
                this@WeatherList,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    fun subscibeWeatherObserver() {
        weatherListViewModel?.getWeatherList(cityName)?.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    dataBinding?.progressBar?.visibility=View.GONE
                    if (!it.data.isNullOrEmpty()){
                        dataBinding?.wetherRecyclerView?.adapter =
                                WeatherAdapter(
                                        ArrayList(it.data),
                                        this@WeatherList
                                )
                    }

                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this@WeatherList, it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING ->{
                    dataBinding?.progressBar?.visibility=View.VISIBLE

                }
            }
        })
    }


    /*fun subscibeWeatherObserver() {
        weatherListViewModel?.getWeatherList(cityName)?.observe(this, Observer {
            it?.let { result ->
            if (result != null) {
                if(dataBinding?.wetherRecyclerView?.adapter!=null) {
                    dataBinding?.wetherRecyclerView?.adapter?.notifyDataSetChanged()
                } else {
                    dataBinding?.wetherRecyclerView?.adapter =
                        WeatherAdapter(
                            result,
                            this@WeatherList
                        )
                  }
            }
            }
        })
    }*/

    override fun onWeatherClick(forcastModel: ForcastModel) {
     Toast.makeText(this@WeatherList,"Item Clicked",Toast.LENGTH_SHORT).show()
     }

}