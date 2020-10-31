package it.lucap.android.weather_conditions_kotlin.resources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.network.VolleyResultCallback
import it.lucap.android.weather_conditions_kotlin.network.Webservice
import javax.inject.Inject

class WeatherRepository @Inject constructor() {
    private var dayWeatherCache: LiveData<List<DayWeather>>? = null

    fun fetchAllWeatherDataByCity(city: String): LiveData<List<DayWeather>> {
        if (dayWeatherCache != null && dayWeatherCache!!.value!![0].city.name == city) {
            return dayWeatherCache as LiveData<List<DayWeather>>
        }
        val data = MutableLiveData<List<DayWeather>>()
        dayWeatherCache = data
        Webservice.fetchWeatherDataByCity(city, object : VolleyResultCallback<List<DayWeather>> {
            override fun onSuccess(result: List<DayWeather>) {
                data.value = result
            }
        })
        return data
    }
}