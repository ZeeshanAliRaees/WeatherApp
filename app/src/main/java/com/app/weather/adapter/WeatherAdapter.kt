package com.app.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.R
import com.app.weather.databinding.ItemForcastListBinding
import com.app.weather.listeners.WeatherClickListener
import com.app.weather.models.ForcastModel
import com.app.weather.util.Tools


class WeatherAdapter(private val itemList: ArrayList<ForcastModel>?, private var weatherClickListener: WeatherClickListener) :
      RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemForcastListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_forcast_list, parent, false)
        binding.clickListener=weatherClickListener
        return ViewHolder(binding)
    }

    class ViewHolder(itemBinding: ItemForcastListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var binding: ItemForcastListBinding=itemBinding
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forcastModel=itemList?.get(position)
        if(forcastModel!=null) {
            holder.binding.item = forcastModel
            if (forcastModel.weather?.get(0) != null &&
                Tools.hasValue(forcastModel.weather?.get(0)?.main)) {
                holder.binding.txtWeatherCondition.text=forcastModel.weather?.get(0)?.main
            }

            if (Tools.hasValue(forcastModel.dt_txt)) {
                holder.binding.txtDateFormat.text=Tools.convertDateToFormt(forcastModel.dt_txt)
            }
        }
    }
 }