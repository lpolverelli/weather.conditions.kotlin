package it.lucap.android.weather_conditions_kotlin.model

import org.json.JSONObject

data class City (
    val id: Int,
    val name: String,
    val coordinates: Coordinates,
    val country: String,
    val timezone: Int
) {
    companion object Factory {
        fun fromJson(json: JSONObject): City =
            City(
                json["id"] as Int,
                json["name"] as String,
                Coordinates.fromJson(JSONObject(json["coord"].toString())),
                json["country"] as String,
                json["timezone"] as Int
            )
    }
}