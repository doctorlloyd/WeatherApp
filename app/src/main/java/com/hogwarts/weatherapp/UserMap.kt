package com.hogwarts.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.hogwarts.weatherapp.adapters.ContentProvider
import com.hogwarts.weatherapp.adapters.LocationAdapter
import com.hogwarts.weatherapp.models.LocationWeather
import com.hogwarts.weatherapp.network.Routes
import com.hogwarts.weatherapp.network.ServiceManager
import kotlinx.android.synthetic.main.layout_list.*
import kotlinx.android.synthetic.main.weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.math.roundToInt


class UserMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var context: Context
    private val API_KEY = "ac8112a61cde5064a3cffb756e6195f7"
    private lateinit var arrayList: ArrayList<Address>
    private lateinit var _adapter: LocationAdapter
    private lateinit var list: ArrayList<Location>
    private var clicked = true
    private lateinit var progress: ContentProvider
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userapp)
        progress = ContentProvider(this)
        context = this
        arrayList = ArrayList()
        list = ArrayList()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        progress.createDialog()
        if (networkAvailable())
            getLocation()
        else
            Toast.makeText(this, "Please check your network and refresh the app", Toast.LENGTH_LONG)
                .show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    fun setLocation(cityName: String, list: List<Address>) {

        for (loc in list) {
            val location = LatLng(loc.latitude, loc.longitude)
            mMap.addMarker(MarkerOptions().position(location).title(cityName))
        }
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    list[0].latitude,
                    list[0].longitude
                ), 10F
            )
        )
    }

    private fun getLocation(): List<Address> {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        var addresses: List<Address> = ArrayList()
        val locationListener = object : LocationListener {
            @SuppressLint("SetTextI18n")
            override fun onLocationChanged(location: Location?) {
                val latitude = location!!.latitude
                val longitude = location.longitude

                val geocoder = Geocoder(context, Locale.getDefault())
                addresses = geocoder.getFromLocation(latitude, longitude, 1)

                Log.i("test", "Response: ${addresses.size}")

                val url = "https://api.openweathermap.org/data/2.5/weather?zip=" +
                        "${addresses[0].postalCode},${addresses[0].countryCode}&appid=$API_KEY"
                if (clicked)
                    list.add(location)
                clicked = false
                setLocation(addresses[0].getAddressLine(0), addresses)

                thread {
                    val gson = Gson()
                    val jsonStr = try {
                        URL(url).readText()
                    } catch (ex: Exception) {
                        return@thread
                    }
                    runOnUiThread {
                        val testModel = gson.fromJson(jsonStr, LocationWeather::class.java)
                        when {
                            testModel.weather[0].main.contains(
                                "cloud",
                                true
                            ) -> weather_icon.setImageDrawable(
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_action_cloud
                                )
                            )
                            testModel.weather[0].main.contains(
                                "sun",
                                true
                            ) -> weather_icon.setImageDrawable(
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_action_sunny
                                )
                            )
                            testModel.weather[0].main.contains(
                                "clear",
                                true
                            ) -> weather_icon.setImageDrawable(
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_action_clear
                                )
                            )
                        }
                        weather.text = testModel.weather[0].main
                        max_temp.text =
                            "Max temp: ${(testModel.main.temp_max / 10).roundToInt()}°C " +
                                    "\nWind speed: ${testModel.wind.speed.roundToInt()}Km/H"
                        weather_description.text = "Type: ${testModel.weather[0].description}."
                        weather_city.text = testModel.name

                        _adapter = LocationAdapter(addresses)
                        list_item.adapter = _adapter
                        progress.hideDialog()
                        _adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION
            )
        }
        locationManager!!.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0L,
            0f,
            locationListener
        )

        return addresses
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
                PackageManager.PERMISSION_DENIED -> {
                    //Set location
                }
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.network -> {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            true
        }
        R.id.add_location -> {
            clicked = true
            getLocation()
            title = "Location History"
            list_item_layout.visibility = View.VISIBLE
            weather_card_layout.visibility = View.GONE
            true
        }
        R.id.back_action_icon -> {
            startActivity(Intent(this, UserMap::class.java))
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.network, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun networkAvailable(): Boolean {
        val connectionManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val active = connectionManager.activeNetwork
        return active != null && connectionManager.isDefaultNetworkActive
    }

    override fun onBackPressed() {
        if (counter < 1) {
            Toast.makeText(
                this,
                "Press back button again to exit..", Toast.LENGTH_SHORT
            ).show()
            counter++
        } else {
            counter = 0
            super.onBackPressed()
        }
    }
}