package it.lucap.android.weather_conditions_kotlin.resources

import androidx.lifecycle.MutableLiveData
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.network.VolleyResultCallback
import it.lucap.android.weather_conditions_kotlin.network.Webservice
import javax.inject.Inject

class WeatherRepository @Inject constructor() {
    val dayWeatherCache: MutableLiveData<List<DayWeather>> = MutableLiveData<List<DayWeather>>()

    fun fetchAllWeatherDataByCity(city: String) {
        Webservice.fetchWeatherDataByCity(city, object : VolleyResultCallback<List<DayWeather>> {
            override fun onSuccess(result: List<DayWeather>) {
                dayWeatherCache.value = result
            }
        })
    }
}