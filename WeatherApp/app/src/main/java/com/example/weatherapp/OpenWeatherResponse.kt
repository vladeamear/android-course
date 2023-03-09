package com.example.weatherapp
import java.math.BigInteger

data class OpenWeatherCoords(val lon: Double, val lat: Double)
data class OpenWeatherWeather(
    val id: Int,
    val main: String,
    val description: String, val icon: String
)
data class OpenWeatherMain(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double,
    val sea_level: Double,
    val grnd_level: Double
)
data class OpenWeatherWind(
    val speed: Double,
    val deg: Double,
    val gust: Double,
)
data class OpenWeatherSys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: BigInteger,
    val sunset: BigInteger,
)
data class OpenWeatherResponse(
    val coord: OpenWeatherCoords,
    val weather: Array<OpenWeatherWeather>,
    val base: String,
    val main: OpenWeatherMain,
    val visibility: Integer,
    val wind: OpenWeatherWind,
    val dt: BigInteger,
    val sys: OpenWeatherSys,
    val timezone: Int,
    val id: BigInteger,
    val name: String,
    val cod: Int,
)