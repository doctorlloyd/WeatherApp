package com.hogwarts.weatherapp.adapters

import android.location.Address
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hogwarts.weatherapp.R
import com.hogwarts.weatherapp.models.Weather

class LocationAdapter (private val locations: List<Address>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

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
        val location = locations[position]
        viewHolder.weatherName.text = location.countryName
        viewHolder.weatherAddress.text = location.adminArea
        viewHolder.weatherCapabilities.text = location.locality
        viewHolder.weatherVenueName.text = location.featureName
    }

    override fun getItemCount(): Int {
        return locations.size
    }
}