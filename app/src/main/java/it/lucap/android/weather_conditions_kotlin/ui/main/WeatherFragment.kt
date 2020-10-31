package it.lucap.android.weather_conditions_kotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import it.lucap.android.weather_conditions_kotlin.R
import it.lucap.android.weather_conditions_kotlin.network.Webservice

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Webservice.init(requireContext())
        val savedCity: String? = viewModel.savedStateHandle["city"]
        if (savedCity == null) viewModel.setCity("Cesena")
        viewModel.dayWeatherList.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.city).text = it[0].city.name
        }
    }

    fun setCity(city: String) {
        viewModel.setCity(city)
    }

}