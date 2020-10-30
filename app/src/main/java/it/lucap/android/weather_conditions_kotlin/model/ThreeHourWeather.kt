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
                Date((json["dt"] as Long) * 1000),
                if (JSONObject(json["main"] as String)["temp"] is Int) JSONObject(json["main"] as String)["temp"] as Int else
                (JSONObject(json["main"] as String)["temp"] as Double).roundToInt(),
                WeatherDetails.fromJson(JSONArray(json["weather"] as String)[0] as JSONObject)
            )
    }
}