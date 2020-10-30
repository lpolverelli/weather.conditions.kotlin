package it.lucap.android.weather_conditions_kotlin

import it.lucap.android.weather_conditions_kotlin.model.City
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CityTest {

    @Mock
    private lateinit var mockJson: JSONObject

    @Test
    fun city_should_be_decoded_from_json() {

        val coordinateString = """{ "lat": 34.257, "lon": -85.1647 }"""

        Mockito.`when`(mockJson["id"]).thenReturn(32643743)
        Mockito.`when`(mockJson["name"]).thenReturn("London")
        Mockito.`when`(mockJson["coord"]).thenReturn(coordinateString)
        Mockito.`when`(mockJson["country"]).thenReturn("GB")
        Mockito.`when`(mockJson["timezone"]).thenReturn(0)

        val city = City.fromJson(mockJson)

        Assert.assertEquals(city.id, 32643743)
        Assert.assertEquals(city.name, "London")
        Assert.assertEquals(city.coordinates.latitude, 34.257, 0.0001)
        Assert.assertEquals(city.country, "GB")
        Assert.assertEquals(city.timezone, 0)
    }
}