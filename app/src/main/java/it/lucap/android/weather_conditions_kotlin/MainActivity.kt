package it.lucap.android.weather_conditions_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.ui.main.WeatherFragment


const val EXTRA_DAY_WEATHER = "it.lucap.android.weather_condition_kotlin.DAY_WEATHER"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, WeatherFragment.newInstance(), "main_fragment")
                    .commitNow()
        }
        setSupportActionBar(findViewById(R.id.toolbar_main))
        supportActionBar?.title = "5 Days Weather"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val search = menu.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val fragment = supportFragmentManager.findFragmentByTag("main_fragment") as WeatherFragment
                if (query != null) {
                    fragment.setCity(query)
                    supportFragmentManager.beginTransaction()
                        .detach(fragment)
                        .attach(fragment)
                        .commit()
                }
                searchView.setQuery("", false)
                searchView.isIconified = true
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    fun moveToWeatherDayPage(dayWeather: DayWeather) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            val stringDayWeather = Gson().toJson(dayWeather)
            putExtra(EXTRA_DAY_WEATHER, stringDayWeather)
        }
        startActivity(intent)
    }

}