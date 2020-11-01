package it.lucap.android.weather_conditions_kotlin.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.lucap.android.weather_conditions_kotlin.R
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.ui.adapters.DetailAdapter

class DetailFragment(private val dayWeather: DayWeather) : Fragment() {

    companion object {
        fun newInstance(dayWeather: DayWeather) = DetailFragment(dayWeather)
    }

    private lateinit var  recyclerView: RecyclerView
    private lateinit var adapter: DetailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.detailRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        adapter = DetailAdapter(requireContext(), dayWeather.threeHourWeatherList)
        recyclerView.adapter = adapter
    }
}