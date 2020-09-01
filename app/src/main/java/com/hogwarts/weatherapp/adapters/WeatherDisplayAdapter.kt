package com.hogwarts.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hogwarts.weatherapp.R
import com.hogwarts.weatherapp.models.ViewModel
import com.hogwarts.weatherapp.models.Weather

class WeatherDisplayAdapter (private val context: Context, private val weather: List<Weather>) :
    RecyclerView.Adapter<WeatherDisplayAdapter.ViewHolder>() {
    private val model = ViewModel(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var wifiName: TextView = itemView.findViewById(R.id.wifi_name)
        var wifiAddress: TextView = itemView.findViewById(R.id.wifi_address)
        var wifiCapabilities: TextView = itemView.findViewById(R.id.wifi_capabilities)
        var wifiStrength: TextView = itemView.findViewById(R.id.wifi_strength)
        var wifiVenueName: TextView = itemView.findViewById(R.id.wifi_venueName)
        var wifiCardView: CardView = itemView.findViewById(R.id.wifi_card_view)

        init {
//            wifiCardView.setOnClickListener { view: View ->
//                val intent = Intent(context, DisplayScreen::class.java)
//                intent.putExtra("selected", weather[adapterPosition])
//                view.context.startActivity(intent)
//            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): WeatherDisplayAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.weather_card, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherDisplayAdapter.ViewHolder, position: Int) {
        val wifi = weather[position]
//        viewHolder.wifiName.text = wifi.name
//        viewHolder.wifiAddress.text = wifi.address
//        viewHolder.wifiCapabilities.text = wifi.capabilities
//        viewHolder.wifiStrength.text = model.signalCheck(wifi.strength)
//        viewHolder.wifiVenueName.text = wifi.venueName
    }

    override fun getItemCount(): Int {
        return weather.size
    }
}