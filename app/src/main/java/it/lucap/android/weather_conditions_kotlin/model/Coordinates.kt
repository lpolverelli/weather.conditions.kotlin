package it.lucap.android.weather_conditions_kotlin.model

import org.json.JSONObject

data class Coordinates (
    val latitude: Double,
    val longitude: Double
) {
    companion object Factory {
        fun fromJson(json: JSONObject): Coordinates =
            Coordinates(
                json["lat"] as Double,
                json["lon"] as Double
            )
    }
}