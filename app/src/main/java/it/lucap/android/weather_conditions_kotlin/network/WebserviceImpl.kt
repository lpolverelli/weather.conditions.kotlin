package it.lucap.android.weather_conditions_kotlin.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import it.lucap.android.weather_conditions_kotlin.model.City
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.model.ThreeHourWeather
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class WebserviceImpl(private val queue: RequestQueue): Webservice {

    override fun fetchWeatherDataByCity(
        city: String,
        callback: VolleyResultCallback<List<DayWeather>>
    ) {
        val stringRequest = JsonObjectRequest(Request.Method.GET, "${URL}q=$city&$URL_UNIT_LANG&appid=$API_KEY", null,
            {response ->
                callback.onSuccess(parseDayWeather(response))
            },
            {
                throw Exception("Failed to get the resources")
            }
        )
        queue.add(stringRequest)
    }

    private fun parseDayWeather(responseBody: JSONObject): List<DayWeather> {
        val city = City.fromJson(JSONObject(responseBody["city"] as String))
        val list = JSONArray(responseBody["list"] as String)
        val threeHourWeatherList = mutableListOf<ThreeHourWeather>()
        for (index in 0 until list.length()) {
            threeHourWeatherList.add(ThreeHourWeather.fromJson(list.getJSONObject(index)))
        }
        threeHourWeatherList.sortBy { it.date }
        val groupedThreeHourWeatherList = groupThreeHourWeatherByDate(threeHourWeatherList)
        val dayWeatherList = mutableListOf<DayWeather>()
        groupedThreeHourWeatherList.forEach { dayWeatherList.add(DayWeather(city, it[0].date, it)) }
        return dayWeatherList
    }

    private fun groupThreeHourWeatherByDate(threeHourWeatherList: List<ThreeHourWeather>): List<List<ThreeHourWeather>> {
        val result = mutableListOf<List<ThreeHourWeather>>()
        val currentDate = Calendar.getInstance()
        currentDate.time = threeHourWeatherList[0].date
        val currentDayList = mutableListOf<ThreeHourWeather>()

        threeHourWeatherList.forEach {
            val newDate = Calendar.getInstance()
            newDate.time = it.date
            if(newDate.get(Calendar.YEAR) > currentDate.get(Calendar.YEAR) ||
                newDate.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH) ||
                newDate.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH)) {
                result.add(currentDayList.toList())
                currentDate.time = it.date
                currentDayList.clear()
            }
            currentDayList.add(it)
        }
        result.add(currentDayList.toList())

        return result
    }

    companion object {
        const val URL = "https://api.openweathermap.org/data/2.5/forecast?"
        const val URL_UNIT_LANG = "units=metric&lang=it"
        const val API_KEY = ""
    }
}