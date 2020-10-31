package it.lucap.android.weather_conditions_kotlin.network

interface VolleyResultCallback<T> {
    fun onSuccess(result: T)
}