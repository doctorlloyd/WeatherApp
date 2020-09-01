package com.hogwarts.weatherapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.hogwarts.weatherapp.adapters.WiFiDisplayAdapter
import com.hogwarts.weatherapp.models.ViewModel
import com.hogwarts.weatherapp.models.WiFi
import kotlinx.android.synthetic.main.user_map.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var wifiManager: WifiManager
    private lateinit var arrayList: ArrayList<WiFi>
    private lateinit var _adapter: WiFiDisplayAdapter
    private lateinit var model: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_map)
        model = ViewModel(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        arrayList = ArrayList()
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        enableWifi()

        scanBtn.setOnClickListener {
            scanWifi()
        }

        _adapter = WiFiDisplayAdapter(this, arrayList)
        wifiList.adapter = _adapter
        scanWifi()
        _adapter.notifyDataSetChanged()
    }

    private fun enableWifi() {
        if (!wifiManager.isWifiEnabled) {
            Toast.makeText(this, "WiFi is disabled ... I am enabling it", Toast.LENGTH_LONG).show()
            wifiManager.isWifiEnabled = true
        }
    }

    private fun scanWifi() {
        arrayList.clear()
        registerReceiver(this.wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show()
        _adapter.notifyDataSetChanged()
    }

    private var wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {
            val results = wifiManager.scanResults
            Log.i("test", "Response: $results")

            unregisterReceiver(this)
            for (scanResult in results) {
                arrayList.add(
                    WiFi(
                        scanResult.SSID,
                        scanResult.level,
                        scanResult.capabilities,
                        scanResult.BSSID,
                        scanResult.venueName.toString()
                    )
                )
                _adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        unregisterReceiver(wifiReceiver)
    }

    override fun onBackPressed() {
        startActivity(Intent(this,UserMap::class.java))
        unregisterReceiver(wifiReceiver)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add_list -> {
            model.postWifiList(arrayList)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
