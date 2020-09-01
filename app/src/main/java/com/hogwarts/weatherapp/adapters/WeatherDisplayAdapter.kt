package com.hogwarts.weatherapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hogwarts.weatherapp.DisplayScreen
import com.hogwarts.weatherapp.R
import com.hogwarts.weatherapp.models.ViewModel
import com.hogwarts.weatherapp.models.Weather

class WeatherDisplayAdapter (private val weather: List<Weather>) :
    RecyclerView.Adapter<WeatherDisplayAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weatherName: TextView = itemView.findViewById(R.id.weather_name)
        var weatherAddress: TextView = itemView.findViewById(R.id.weather_address)
        var weatherCapabilities: TextView = itemView.findViewById(R.id.weather_capabilities)
        var weatherVenueName: TextView = itemView.findViewById(R.id.weather_venueName)
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_card, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val weather = weather[position]
        viewHolder.weatherName.text = weather.main
        viewHolder.weatherAddress.text = weather.icon
        viewHolder.weatherCapabilities.text = weather.description
        viewHolder.weatherVenueName.text = weather.id.toString()
    }

    override fun getItemCount(): Int {
        return weather.size
    }
}