package it.lucap.android.weather_conditions_kotlin.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.lucap.android.weather_conditions_kotlin.R
import it.lucap.android.weather_conditions_kotlin.model.ThreeHourWeather
import it.lucap.android.weather_conditions_kotlin.ui.viewholders.DetailHolder

class DetailAdapter(private val context: Context, private val threeHourWeatherList: List<ThreeHourWeather>) : RecyclerView.Adapter<DetailHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_card, parent, false)
        return  DetailHolder(view)
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.setDetails(threeHourWeatherList[position])
    }

    override fun getItemCount(): Int = threeHourWeatherList.size

}