package it.lucap.android.weather_conditions_kotlin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import it.lucap.android.weather_conditions_kotlin.MainActivity
import it.lucap.android.weather_conditions_kotlin.R
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.network.Webservice
import it.lucap.android.weather_conditions_kotlin.ui.adapters.DayWeatherAdapter

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DayWeatherAdapter
    private val dayWeatherList = mutableListOf<DayWeather>()

    private val onItemClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as RecyclerView.ViewHolder
        val position = viewHolder.adapterPosition
        (activity as MainActivity).moveToWeatherDayPage(dayWeatherList[position])
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Webservice.init(requireContext())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        adapter = DayWeatherAdapter(requireContext(), dayWeatherList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(onItemClickListener)
        val savedCity: String? = viewModel.savedStateHandle["city"]
        if (savedCity == null) viewModel.setCity("Cesena")
        viewModel.dayWeatherList.observe(viewLifecycleOwner) {
            val cityTextView = view.findViewById<TextView>(R.id.city)
            cityTextView.text = it[0].city.name + ", " + it[0].city.country
            dayWeatherList.clear()
            it.forEach { dayWeather -> dayWeatherList.add(dayWeather) }
            adapter.notifyDataSetChanged()
            view.findViewById<ProgressBar>(R.id.progress_spinner).visibility = View.GONE
            cityTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
        }
    }

    fun setCity(city: String) {
        viewModel.setCity(city)
    }

}