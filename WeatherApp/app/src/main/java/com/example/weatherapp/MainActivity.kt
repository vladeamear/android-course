package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import java.net.URL

class MainActivity : AppCompatActivity() {
    var LAT: String = "59.8944"
    var LON: String = "30.2642"
//    Ширина и долгота Санкт Петербурга
    val API_ID: String = "16d5ab1b9cb848d337b649f4952d2896"
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val gson = Gson();

    private fun getCity(lat: Double, lng: Double): String? {
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(lat, lng, 1)
        return list?.get(0)?.getLocality()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        findViewById<FloatingActionButton>(R.id.update_info).setOnClickListener {
            fetchLocation()
        }
        fetchLocation()
    }

    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation
        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }

        task.addOnSuccessListener {
            if (it != null) {
                val city = getCity(it.latitude, it.longitude)
                LON = it.longitude.toString()
                LAT = it.latitude.toString()
                findViewById<TextView>(R.id.city_title).text = city
                weatherTask().execute()
            }
        }
    }

    inner class weatherTask(): AsyncTask<String, Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?lat=$LAT&lon=$LON&units=metric&appid=$API_ID&lang=ru")
                    .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                response = null
            }
            return response.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val resObj: OpenWeatherResponse = gson.fromJson(result, OpenWeatherResponse::class.java)
            try {
                findViewById<TextView>(R.id.current_degree).text =
                    "${Math.round(resObj.main.temp)}°"
                findViewById<TextView>(R.id.current_feel_degree).text =
                    "${Math.round(resObj.main.feels_like)}°"
                findViewById<TextView>(R.id.weather_description).text =
                    resObj.weather.get(0).description.capitalize()
                findViewById<TextView>(R.id.humidity).text =
                    "${resObj.main.humidity}%"
                findViewById<TextView>(R.id.wind_speed).text =
                    "${resObj.wind.speed} м/с"
            } catch (e: Exception) {
                findViewById<TextView>(R.id.current_degree).text = "Error"
            }
        }
    }
}