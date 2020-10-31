package it.lucap.android.weather_conditions_kotlin

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import it.lucap.android.weather_conditions_kotlin.ui.main.WeatherFragment


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WeatherFragment.newInstance(), "main_fragment")
                    .commitNow()
        }
        setSupportActionBar(findViewById(R.id.toolbar))
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
                }
                searchView.setQuery("", false);
                searchView.isIconified = true;
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

}