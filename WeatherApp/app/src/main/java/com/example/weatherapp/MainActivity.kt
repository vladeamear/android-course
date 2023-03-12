package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar

val timeGradient: Array<IntArray> = arrayOf(
    intArrayOf(0xFF00000Cu.toInt(), 0xFF00000Cu.toInt()),
    intArrayOf(0xFF020111u.toInt(), 0xFF191621u.toInt()),
    intArrayOf(0xFF020111u.toInt(), 0xFF20202cu.toInt()),
    intArrayOf(0xFF020111u.toInt(), 0xFF3a3a52u.toInt()),
    intArrayOf(0xFF20202cu.toInt(), 0xFF515175u.toInt()),
    intArrayOf(0xFF40405cu.toInt(), 0xFF6f71aau.toInt(), 0xFF8a76abu.toInt()),
    intArrayOf(0xFF4a4969u.toInt(), 0xFF7072abu.toInt(), 0xFFcd82a0u.toInt()),
    intArrayOf(0xFF757abfu.toInt(), 0xFF8583beu.toInt(), 0xFFeab0d1u.toInt()),
    intArrayOf(0xFF82addbu.toInt(), 0xFFebb2b1u.toInt()),
    intArrayOf(0xFF94c5f8u.toInt(), 0xFFa6e6ffu.toInt(), 0xFFb1b5eau.toInt()),
    intArrayOf(0xFFb7eaffu.toInt(), 0xFF94dfffu.toInt()),
    intArrayOf(0xFF9be2feu.toInt(), 0xFF67d1fbu.toInt()),
    intArrayOf(0xFF90dffeu.toInt(), 0xFF38a3d1u.toInt()),
    intArrayOf(0xFF57c1ebu.toInt(), 0xFF246fa8u.toInt()),
    intArrayOf(0xFF2d91c2u.toInt(), 0xFF1e528eu.toInt()),
    intArrayOf(0xFF2473abu.toInt(), 0xFF1e528eu.toInt(), 0xFF5b7983u.toInt()),
    intArrayOf(0xFF1e528eu.toInt(), 0xFF265889u.toInt(), 0xFF9da671u.toInt()),
    intArrayOf(0xFF1e528eu.toInt(), 0xFF728a7cu.toInt(), 0xFFe9ce5du.toInt()),
    intArrayOf(0xFF154277u.toInt(), 0xFF576e71u.toInt(), 0xFFe1c45eu.toInt(), 0xFFb26339u.toInt()),
    intArrayOf(0xFF163C52u.toInt(), 0xFF4F4F47u.toInt(), 0xFFC5752Du.toInt(), 0xFFB7490Fu.toInt(), 0xFF2F1107u.toInt()),
    intArrayOf(0xFF071B26u.toInt(), 0xFF071B26u.toInt(), 0xFF8A3B12u.toInt(), 0xFF240E03u.toInt()),
    intArrayOf(0xFF010A10u.toInt(), 0xFF59230Bu.toInt(), 0xFF2F1107u.toInt()),
    intArrayOf(0xFF090401u.toInt(), 0xFF4B1D06u.toInt()),
    intArrayOf(0xFF00000cu.toInt(), 0xFF150800u.toInt()),
)

class MainActivity : AppCompatActivity() {
    var LAT: String = "59.8944"
    var LON: String = "30.2642"
//    Ширина и долгота Санкт Петербурга
    val API_ID: String = "16d5ab1b9cb848d337b649f4952d2896"
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val gson = Gson();

    private fun getCity(lat: Double, lng: Double): String? {
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(lat, lng, 1)
        return list?.get(0)?.locality
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
                    resObj.weather[0].description.capitalize()
                findViewById<TextView>(R.id.humidity).text =
                    "${resObj.main.humidity}%"
                findViewById<TextView>(R.id.wind_speed).text =
                    "${resObj.wind.speed} м/с"
                val resourceId =
                    resources.getIdentifier("ic_${resObj.weather[0].icon}", "drawable", packageName)
                findViewById<ImageView>(R.id.imageView).setImageResource(resourceId)
                findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.wrapper).background =
                    GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, timeGradient[currentHour])
            } catch (e: Exception) {
                findViewById<TextView>(R.id.current_degree).text = "Error"
            }
        }
    }
}