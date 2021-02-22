package com.app.weather.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
 import com.app.weather.databinding.FragmentWeatherDetailBinding
 import com.app.weather.network.RemoteDataSource
import com.app.weather.network.RetrofitClient
import com.app.weather.viewmodels.ViewModelFactory
 import com.app.weather.repository.MainRepository
import com.app.weather.room.DatabaseBuilder
import com.app.weather.viewmodels.WeatherListViewModel

class WeatherDetailFragment : Fragment() {
    private lateinit var detailFragmentBinding: FragmentWeatherDetailBinding
    private lateinit var weatherListViewModel: WeatherListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         detailFragmentBinding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        detailFragmentBinding.item=weatherListViewModel.detailItem
        return detailFragmentBinding.root    }

    private fun initializeViewModel()
    {
        activity?.run {
            val viewModelFactory= ViewModelFactory(
                MainRepository(
                    RemoteDataSource( RetrofitClient.apiInterface),
                    DatabaseBuilder.getInstance(this).daoAccess()!!))
            weatherListViewModel= ViewModelProvider(this,viewModelFactory).get(WeatherListViewModel::class.java)
        }
    }

}