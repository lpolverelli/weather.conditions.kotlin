package it.lucap.android.weather_conditions_kotlin

import it.lucap.android.weather_conditions_kotlin.model.Coordinates
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CoordinatesTest {

    @Mock
    private lateinit var mockJson: JSONObject

    @Test
    fun coordinates_should_be_decoded_from_json() {

        `when`(mockJson["lat"]).thenReturn(34.257)
        `when`(mockJson["lon"]).thenReturn(-85.1647)

        val coordinates = Coordinates.fromJson(mockJson)

        assertEquals(coordinates.latitude, 34.257, 0.0001)
        assertEquals(coordinates.longitude, -85.1647, 0.0001)
    }
}