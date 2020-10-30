package it.lucap.android.weather_conditions_kotlin.model

import java.util.*

data class DayWeather (
    val city: City,
    val date: Date,
    val threeHourWeatherList: List<ThreeHourWeather>
) {
    val maxTemperature = threeHourWeatherList.maxByOrNull { it.temperature }?.temperature
    val minTemperature = threeHourWeatherList.minByOrNull { it.temperature }?.temperature
}