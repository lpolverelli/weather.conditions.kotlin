package it.lucap.android.weather_conditions_kotlin.model

import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.math.roundToInt

data class ThreeHourWeather (
    val date: Date,
    val temperature: Int,
    val weatherDetails: WeatherDetails
) {
    companion object Factory {
        fun fromJson(json: JSONObject): ThreeHourWeather =
            ThreeHourWeather(
                Date((json["dt"] as Int).toLong() * 1000),
                if (JSONObject(json["main"].toString())["temp"] is Int) JSONObject(json["main"].toString())["temp"] as Int else
                (JSONObject(json["main"].toString())["temp"] as Double).roundToInt(),
                WeatherDetails.fromJson(JSONArray(json["weather"].toString())[0] as JSONObject)
            )
    }
}