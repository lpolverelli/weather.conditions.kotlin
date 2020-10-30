package it.lucap.android.weather_conditions_kotlin

import it.lucap.android.weather_conditions_kotlin.model.WeatherDetails
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherDetailsTest {

    @Mock
    private lateinit var mockJson: JSONObject

    @Test
    fun weather_details_should_be_decoded_from_json() {

        Mockito.`when`(mockJson["id"]).thenReturn(804)
        Mockito.`when`(mockJson["main"]).thenReturn("Clouds")
        Mockito.`when`(mockJson["description"]).thenReturn("overcast clouds")
        Mockito.`when`(mockJson["icon"]).thenReturn("04n")

        val weatherDetails = WeatherDetails.fromJson(mockJson)

        assertEquals(weatherDetails.id, 804)
        assertEquals(weatherDetails.main, "Clouds")
        assertEquals(weatherDetails.description, "overcast clouds")
        assertEquals(weatherDetails.icon, "04n")
    }

}