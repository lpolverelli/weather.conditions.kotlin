package it.lucap.android.weather_conditions_kotlin.model

import org.json.JSONObject

data class WeatherDetails (
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) {
    companion object Factory {
        fun fromJson(json: JSONObject): WeatherDetails =
            WeatherDetails(
                json["id"] as Int,
                json["main"] as String,
                json["description"] as String,
                json["icon"] as String
            )

    }
}