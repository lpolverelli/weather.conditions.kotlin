package it.lucap.android.weather_conditions_kotlin.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import it.lucap.android.weather_conditions_kotlin.R
import it.lucap.android.weather_conditions_kotlin.model.ThreeHourWeather
import java.text.DecimalFormat
import java.util.*

class DetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewHourDay = itemView.findViewById<TextView>(R.id.hourTextView)
    private val textViewWeatherDescription = itemView.findViewById<TextView>(R.id.detailWeatherDescriptionTextView)
    private val textViewTemperature = itemView.findViewById<TextView>(R.id.detailTemperatureTextView)
    private val imageViewWeather = itemView.findViewById<ImageView>(R.id.detailImageView)

    fun setDetails(threeHourWeather: ThreeHourWeather) {
        val calendar = Calendar.getInstance()
        calendar.time = threeHourWeather.date
        val format = DecimalFormat("00")
        textViewHourDay.text = format.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":00"
        textViewWeatherDescription.text = threeHourWeather.weatherDetails.description
        textViewTemperature.text = "T: " + threeHourWeather.temperature + "°C"
        Picasso.get().load(IMAGE_URI + threeHourWeather.weatherDetails.icon + "@4x.png").into(
            imageViewWeather
        )
    }

    companion object {
        private const val IMAGE_URI = "https://openweathermap.org/img/wn/"
    }
}