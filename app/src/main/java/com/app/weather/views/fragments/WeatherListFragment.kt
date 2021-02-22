package com.app.weather.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
 import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weather.R
import com.app.weather.adapter.WeatherAdapter
import com.app.weather.databinding.FragmentWeatherListBinding
import com.app.weather.listeners.WeatherClickListener
import com.app.weather.models.ForcastModel
import com.app.weather.network.RemoteDataSource
import com.app.weather.network.Resource
import com.app.weather.network.RetrofitClient
import com.app.weather.repository.MainRepository
import com.app.weather.room.DatabaseBuilder
import com.app.weather.util.Tools
import com.app.weather.viewmodels.WeatherListViewModel
import com.app.weather.viewmodels.ViewModelFactory

class WeatherListFragment : Fragment() , WeatherClickListener {
    private lateinit var weatherFragmentBinding: FragmentWeatherListBinding
    private lateinit var weatherListViewModel: WeatherListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weatherFragmentBinding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return weatherFragmentBinding.root
     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerview()
        //Subscribe Observer and call Api
        if(Tools.hasValue(weatherListViewModel.cityName)) {
            subscibeWeatherObserver()
        }
    }

    private fun initializeViewModel()
    {
        activity?.run {
            val viewModelFactory= ViewModelFactory(
                MainRepository(
                    RemoteDataSource( RetrofitClient.apiInterface),
                    DatabaseBuilder.getInstance(this).daoAccess()!!)
            )
            weatherListViewModel= ViewModelProvider(this,viewModelFactory).get(WeatherListViewModel::class.java)
        }
    }

    private fun initializeRecyclerview(){
        weatherFragmentBinding.wetherRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun subscibeWeatherObserver() {
        activity?.run {
            weatherListViewModel.getWeatherList().observe(this, {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        weatherFragmentBinding.progressBar.visibility=View.GONE
                        if (!it.data.isNullOrEmpty()){
                            weatherFragmentBinding.wetherRecyclerView.adapter =
                                WeatherAdapter(
                                    ArrayList(it.data),
                                    this@WeatherListFragment
                                )
                        }
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                    }

                    Resource.Status.LOADING ->{
                        weatherFragmentBinding.progressBar.visibility=View.VISIBLE
                    }
                }
            })
        }
    }

    override fun onWeatherClick(forcastModel: ForcastModel) {
        weatherListViewModel.detailItem=forcastModel
        findNavController().navigate(R.id.list_to_detail)
    }

}