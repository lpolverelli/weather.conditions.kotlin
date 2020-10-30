package it.lucap.android.weather_conditions_kotlin

import it.lucap.android.weather_conditions_kotlin.model.ThreeHourWeather
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class  ThreeHourWeatherTest {

    @Mock
    private lateinit var mockJson: JSONObject

    @Test
    fun three_hour_weather_should_be_decoded_from_json() {

        val mainString = "  {\n" +
                "                \"temp\": 283.91,\n" +
                "                \"feels_like\": 282.28,\n" +
                "                \"temp_min\": 283.91,\n" +
                "                \"temp_max\": 283.91,\n" +
                "                \"pressure\": 1024,\n" +
                "                \"sea_level\": 1024,\n" +
                "                \"grnd_level\": 1001,\n" +
                "                \"humidity\": 77,\n" +
                "                \"temp_kf\": 0\n" +
                "            }"

        val weatherString = "[\n" +
                "                {\n" +
                "                    \"id\": 804,\n" +
                "                    \"main\": \"Clouds\",\n" +
                "                    \"description\": \"overcast clouds\",\n" +
                "                    \"icon\": \"04n\"\n" +
                "                }\n" +
                "            ]"

        Mockito.`when`(mockJson["dt"]).thenReturn(1604232000L)
        Mockito.`when`(mockJson["main"]).thenReturn(mainString)
        Mockito.`when`(mockJson["weather"]).thenReturn(weatherString)

        val threeHourWeather = ThreeHourWeather.fromJson(mockJson)

        Assert.assertEquals(threeHourWeather.date.time, 1604232000L * 1000)
        Assert.assertEquals(threeHourWeather.temperature, 284)
        Assert.assertEquals(threeHourWeather.weatherDetails.id, 804)
    }
}