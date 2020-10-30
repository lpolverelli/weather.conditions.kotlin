package it.lucap.android.weather_conditions_kotlin

import it.lucap.android.weather_conditions_kotlin.model.City
import it.lucap.android.weather_conditions_kotlin.model.DayWeather
import it.lucap.android.weather_conditions_kotlin.model.ThreeHourWeather
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class  DayWeatherTest {

    @Mock
    private lateinit var mockCity: City
    @Mock
    private  lateinit var mockThreeHourWeather1: ThreeHourWeather
    @Mock
    private  lateinit var mockThreeHourWeather2: ThreeHourWeather
    @Mock
    private  lateinit var mockThreeHourWeather3: ThreeHourWeather

    @Test
    fun day_weather_should_be_created_and_should_calculate_max_and_min_temperatures_accurately() {

        Mockito.`when`(mockThreeHourWeather1.temperature).thenReturn(285)
        Mockito.`when`(mockThreeHourWeather2.temperature).thenReturn(286)
        Mockito.`when`(mockThreeHourWeather3.temperature).thenReturn(287)

        val date = Date(1603897200L * 1000)

        Mockito.`when`(mockCity.name).thenReturn("London")

        val threeHourWeatherList = listOf(mockThreeHourWeather1, mockThreeHourWeather2, mockThreeHourWeather3)
        val dayWeather = DayWeather(mockCity, date, threeHourWeatherList)

        Assert.assertEquals(dayWeather.city.name, "London")
        Assert.assertEquals(dayWeather.date, date)
        Assert.assertEquals(dayWeather.maxTemperature, mockThreeHourWeather3.temperature)
        Assert.assertEquals(dayWeather.minTemperature, mockThreeHourWeather1.temperature)

    }
}