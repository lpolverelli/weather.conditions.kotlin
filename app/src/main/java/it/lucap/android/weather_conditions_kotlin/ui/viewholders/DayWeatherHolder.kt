package it.lucap.android.weather_conditions_kotlin.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import it.lucap.android.weather_conditions_kotlin.R
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import java.util.*
import kotlin.math.roundToInt

class DayWeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewDay = itemView.findViewById<TextView>(R.id.dayTextView)
    private val textViewWeatherDescription = itemView.findViewById<TextView>(R.id.weatherDescriptionTextView)
    private val textViewTMax = itemView.findViewById<TextView>(R.id.tMaxTextView)
    private val textViewTMin = itemView.findViewById<TextView>(R.id.tMinTextView)
    private val imageViewWeather = itemView.findViewById<ImageView>(R.id.imageView)

    fun setDetails(dayWeather: DayWeather) {
        val calendar = Calendar.getInstance()
        calendar.time = dayWeather.date

        textViewDay.text = getDay(calendar.get(Calendar.DAY_OF_WEEK)) + " " +
                calendar.get(Calendar.DAY_OF_MONTH) + " " + getMonth(calendar.get(Calendar.MONTH))

        val midHour = dayWeather.threeHourWeatherList[(dayWeather.threeHourWeatherList.size.toDouble() / 2).roundToInt() - 1]
        textViewWeatherDescription.text = midHour.weatherDetails.description
        textViewTMax.text = "Tmax: " + dayWeather.maxTemperature + "°C"
        textViewTMin.text = "Tmin: " + dayWeather.minTemperature + "°C"
        Picasso.get().load(IMAGE_URI + midHour.weatherDetails.icon + "@4x.png").into(imageViewWeather)
    }

    private fun getDay(day: Int): String {
        when(day) {
            Calendar.SUNDAY -> return "Domenica"
            Calendar.MONDAY -> return "Lunedì"
            Calendar.TUESDAY -> return "Martedì"
            Calendar.WEDNESDAY -> return "Mercoledì"
            Calendar.THURSDAY -> return "Giovedì"
            Calendar.FRIDAY -> return "Venerdì"
            Calendar.SATURDAY -> return "Sabato"
        }
        return ""
    }

    private fun getMonth(month: Int): String {
        when(month) {
            Calendar.JANUARY -> return "Gennaio"
            Calendar.FEBRUARY -> return "Febbraio"
            Calendar.MARCH -> return "Marzo"
            Calendar.APRIL -> return "Aprile"
            Calendar.MAY -> return "Maggio"
            Calendar.JUNE -> return "Giugno"
            Calendar.JULY -> return "Luglio"
            Calendar.AUGUST -> return "Agosto"
            Calendar.SEPTEMBER -> return "Settembre"
            Calendar.OCTOBER -> return "Ottobre"
            Calendar.NOVEMBER -> return  "Novembre"
            Calendar.DECEMBER -> return "Dicembre"
        }
        return ""
    }

    companion object {
        private const val IMAGE_URI = "https://openweathermap.org/img/wn/"
    }
}