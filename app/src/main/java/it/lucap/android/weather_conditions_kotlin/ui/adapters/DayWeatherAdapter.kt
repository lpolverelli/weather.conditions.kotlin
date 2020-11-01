package it.lucap.android.weather_conditions_kotlin.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.lucap.android.weather_conditions_kotlin.R
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.ui.viewholders.DayWeatherHolder

class DayWeatherAdapter(private val context: Context, private val dayWeatherList: List<DayWeather> ) : RecyclerView.Adapter<DayWeatherHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWeatherHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return DayWeatherHolder(view)
    }

    override fun onBindViewHolder(holder: DayWeatherHolder, position: Int) {
        holder.setDetails(dayWeatherList[position])
    }

    override fun getItemCount(): Int = dayWeatherList.size

}