package it.lucap.android.weather_conditions_kotlin.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.resources.WeatherRepository

class WeatherViewModel @ViewModelInject constructor(
    @Assisted val savedStateHandle: SavedStateHandle,
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val dayWeatherList: LiveData<List<DayWeather>> = weatherRepository.dayWeatherCache

    fun setCity(city: String) {
        savedStateHandle["city"] = city
        weatherRepository.fetchAllWeatherDataByCity(city)
    }
}