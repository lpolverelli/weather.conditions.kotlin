package it.lucap.android.weather_conditions_kotlin.resources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.network.VolleyResultCallback
import it.lucap.android.weather_conditions_kotlin.network.WebserviceImpl

class WeatherRepository(private val queue: RequestQueue) {
    private val webservice = WebserviceImpl(queue)
    private var dayWeatherCache: LiveData<List<DayWeather>>? = null

    fun fetchAllWeatherDataByCity(city: String): LiveData<List<DayWeather>> {
        val cached = dayWeatherCache
        if (cached != null) {
            return cached
        }
        val data = MutableLiveData<List<DayWeather>>()
        dayWeatherCache = data
        webservice.fetchWeatherDataByCity(city, object : VolleyResultCallback<List<DayWeather>> {
            override fun onSuccess(result: List<DayWeather>) {
                data.value = result
            }
        })
        return data
    }
}