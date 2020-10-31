package it.lucap.android.weather_conditions_kotlin.network

import it.lucap.android.weather_conditions_kotlin.model.DayWeather

interface Webservice {
    fun fetchWeatherDataByCity(city: String, callback: VolleyResultCallback<List<DayWeather>>)
}