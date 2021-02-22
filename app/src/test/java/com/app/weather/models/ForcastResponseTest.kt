package com.app.weather.models
import com.app.weather.util.testutils.AssetReader
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

class ForcastResponseTest{

    var parsedPayload:ForcastResponse?=null

    @Before
    fun setup() {
        val testPayload = """{"cod":"200","message":0,"cnt":40,"list":[{"dt":1613628000,"main":{"temp":271.16,"feels_like":267.09,"temp_min":271.16,"temp_max":272.01,"pressure":1031,"sea_level":1031,"grnd_level":1022,"humidity":73,"temp_kf":-0.85},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03n"}],"clouds":{"all":49},"wind":{"speed":1.92,"deg":95},"visibility":9895,"pop":0.01,"sys":{"pod":"n"},"dt_txt":"2021-02-18 06:00:00"},{"dt":1613638800,"main":{"temp":271.42,"feels_like":267.22,"temp_min":271.42,"temp_max":271.71,"pressure":1029,"sea_level":1029,"grnd_level":1021,"humidity":82,"temp_kf":-0.29},"weather":[{"id":601,"main":"Snow","description":"snow","icon":"13n"}],"clouds":{"all":79},"wind":{"speed":2.37,"deg":50},"visibility":1224,"pop":1,"snow":{"3h":1.52},"sys":{"pod":"n"},"dt_txt":"2021-02-18 09:00:00"},{"dt":1613649600,"main":{"temp":269.82,"feels_like":265.39,"temp_min":269.78,"temp_max":269.82,"pressure":1029,"sea_level":1029,"grnd_level":1021,"humidity":96,"temp_kf":0.04},"weather":[{"id":601,"main":"Snow","description":"snow","icon":"13d"}],"clouds":{"all":94},"wind":{"speed":2.78,"deg":75},"visibility":170,"pop":1,"snow":{"3h":6.68},"sys":{"pod":"d"},"dt_txt":"2021-02-18 12:00:00"},{"dt":1613660400,"main":{"temp":270.28,"feels_like":266.37,"temp_min":270.28,"temp_max":270.28,"pressure":1028,"sea_level":1028,"grnd_level":1020,"humidity":98,"temp_kf":0},"weather":[{"id":601,"main":"Snow","description":"snow","icon":"13d"}],"clouds":{"all":99},"wind":{"speed":2.15,"deg":34},"visibility":484,"pop":1,"snow":{"3h":7.43},"sys":{"pod":"d"},"dt_txt":"2021-02-18 15:00:00"},{"dt":1613671200,"main":{"temp":270.75,"feels_like":266.56,"temp_min":270.75,"temp_max":270.75,"pressure":1026,"sea_level":1026,"grnd_level":1017,"humidity":98,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"clouds":{"all":100},"wind":{"speed":2.63,"deg":60},"visibility":2185,"pop":0.8,"sys":{"pod":"d"},"dt_txt":"2021-02-18 18:00:00"},{"dt":1613682000,"main":{"temp":271.4,"feels_like":267.67,"temp_min":271.4,"temp_max":271.4,"pressure":1023,"sea_level":1023,"grnd_level":1015,"humidity":99,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"clouds":{"all":100},"wind":{"speed":2.12,"deg":335},"visibility":779,"pop":0.8,"sys":{"pod":"d"},"dt_txt":"2021-02-18 21:00:00"},{"dt":1613692800,"main":{"temp":271.31,"feels_like":267.22,"temp_min":271.31,"temp_max":271.31,"pressure":1022,"sea_level":1022,"grnd_level":1014,"humidity":98,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":2.6,"deg":20},"visibility":1310,"pop":0.8,"sys":{"pod":"n"},"dt_txt":"2021-02-19 00:00:00"},{"dt":1613703600,"main":{"temp":271.5,"feels_like":267.12,"temp_min":271.5,"temp_max":271.5,"pressure":1021,"sea_level":1021,"grnd_level":1013,"humidity":99,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":3.07,"deg":359},"visibility":829,"pop":0.8,"sys":{"pod":"n"},"dt_txt":"2021-02-19 03:00:00"},{"dt":1613714400,"main":{"temp":271.42,"feels_like":267.33,"temp_min":271.42,"temp_max":271.42,"pressure":1019,"sea_level":1019,"grnd_level":1011,"humidity":99,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":2.64,"deg":339},"visibility":3522,"pop":0.8,"sys":{"pod":"n"},"dt_txt":"2021-02-19 06:00:00"},{"dt":1613725200,"main":{"temp":271.47,"feels_like":267.01,"temp_min":271.47,"temp_max":271.47,"pressure":1017,"sea_level":1017,"grnd_level":1009,"humidity":99,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":3.18,"deg":353},"visibility":3719,"pop":0.8,"sys":{"pod":"n"},"dt_txt":"2021-02-19 09:00:00"},{"dt":1613736000,"main":{"temp":272.11,"feels_like":268.29,"temp_min":272.11,"temp_max":272.11,"pressure":1017,"sea_level":1017,"grnd_level":1009,"humidity":99,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"clouds":{"all":100},"wind":{"speed":2.39,"deg":344},"visibility":2410,"pop":0.8,"sys":{"pod":"d"},"dt_txt":"2021-02-19 12:00:00"},{"dt":1613746800,"main":{"temp":273.47,"feels_like":269.83,"temp_min":273.47,"temp_max":273.47,"pressure":1018,"sea_level":1018,"grnd_level":1010,"humidity":98,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13d"}],"clouds":{"all":100},"wind":{"speed":2.37,"deg":354},"visibility":10000,"pop":0.56,"snow":{"3h":0.14},"sys":{"pod":"d"},"dt_txt":"2021-02-19 15:00:00"},{"dt":1613757600,"main":{"temp":275.3,"feels_like":271.43,"temp_min":275.3,"temp_max":275.3,"pressure":1017,"sea_level":1017,"grnd_level":1009,"humidity":98,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"clouds":{"all":92},"wind":{"speed":3.11,"deg":326},"visibility":107,"pop":0.48,"sys":{"pod":"d"},"dt_txt":"2021-02-19 18:00:00"},{"dt":1613768400,"main":{"temp":274.86,"feels_like":269.82,"temp_min":274.86,"temp_max":274.86,"pressure":1017,"sea_level":1017,"grnd_level":1009,"humidity":94,"temp_kf":0},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"clouds":{"all":73},"wind":{"speed":4.54,"deg":326},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-19 21:00:00"},{"dt":1613779200,"main":{"temp":271.83,"feels_like":266.06,"temp_min":271.83,"temp_max":271.83,"pressure":1020,"sea_level":1020,"grnd_level":1012,"humidity":95,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03n"}],"clouds":{"all":39},"wind":{"speed":5.01,"deg":317},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-20 00:00:00"},{"dt":1613790000,"main":{"temp":268.9,"feels_like":263.5,"temp_min":268.9,"temp_max":268.9,"pressure":1022,"sea_level":1022,"grnd_level":1014,"humidity":95,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":3.99,"deg":308},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-20 03:00:00"},{"dt":1613800800,"main":{"temp":267.21,"feels_like":261.04,"temp_min":267.21,"temp_max":267.21,"pressure":1023,"sea_level":1023,"grnd_level":1015,"humidity":94,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":4.84,"deg":308},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-20 06:00:00"},{"dt":1613811600,"main":{"temp":265.84,"feels_like":260.09,"temp_min":265.84,"temp_max":265.84,"pressure":1024,"sea_level":1024,"grnd_level":1015,"humidity":94,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":4.06,"deg":311},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-20 09:00:00"},{"dt":1613822400,"main":{"temp":265.03,"feels_like":258.75,"temp_min":265.03,"temp_max":265.03,"pressure":1025,"sea_level":1025,"grnd_level":1017,"humidity":93,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":10},"wind":{"speed":4.71,"deg":312},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-20 12:00:00"},{"dt":1613833200,"main":{"temp":268.3,"feels_like":261.85,"temp_min":268.3,"temp_max":268.3,"pressure":1027,"sea_level":1027,"grnd_level":1019,"humidity":93,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"clouds":{"all":23},"wind":{"speed":5.37,"deg":304},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-20 15:00:00"},{"dt":1613844000,"main":{"temp":271.44,"feels_like":265.28,"temp_min":271.44,"temp_max":271.44,"pressure":1027,"sea_level":1027,"grnd_level":1018,"humidity":93,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"clouds":{"all":13},"wind":{"speed":5.45,"deg":305},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-20 18:00:00"},{"dt":1613854800,"main":{"temp":271.45,"feels_like":265.75,"temp_min":271.45,"temp_max":271.45,"pressure":1027,"sea_level":1027,"grnd_level":1018,"humidity":93,"temp_kf":0},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"clouds":{"all":52},"wind":{"speed":4.79,"deg":310},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-20 21:00:00"},{"dt":1613865600,"main":{"temp":267.13,"feels_like":262.36,"temp_min":267.13,"temp_max":267.13,"pressure":1029,"sea_level":1029,"grnd_level":1021,"humidity":95,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03n"}],"clouds":{"all":27},"wind":{"speed":2.84,"deg":303},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-21 00:00:00"},{"dt":1613876400,"main":{"temp":265.38,"feels_like":260.51,"temp_min":265.38,"temp_max":265.38,"pressure":1031,"sea_level":1031,"grnd_level":1023,"humidity":96,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":2.79,"deg":314},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-21 03:00:00"},{"dt":1613887200,"main":{"temp":264.36,"feels_like":259.63,"temp_min":264.36,"temp_max":264.36,"pressure":1032,"sea_level":1032,"grnd_level":1024,"humidity":95,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":2.45,"deg":324},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-21 06:00:00"},{"dt":1613898000,"main":{"temp":263.79,"feels_like":259.28,"temp_min":263.79,"temp_max":263.79,"pressure":1034,"sea_level":1034,"grnd_level":1025,"humidity":95,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":2.07,"deg":324},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-21 09:00:00"},{"dt":1613908800,"main":{"temp":263.22,"feels_like":259.3,"temp_min":263.22,"temp_max":263.22,"pressure":1035,"sea_level":1035,"grnd_level":1026,"humidity":95,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.18,"deg":316},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-21 12:00:00"},{"dt":1613919600,"main":{"temp":270.38,"feels_like":267.39,"temp_min":270.38,"temp_max":270.38,"pressure":1035,"sea_level":1035,"grnd_level":1027,"humidity":94,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":0.77,"deg":328},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-21 15:00:00"},{"dt":1613930400,"main":{"temp":272.89,"feels_like":270.1,"temp_min":272.89,"temp_max":272.89,"pressure":1033,"sea_level":1033,"grnd_level":1025,"humidity":95,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":0.95,"deg":164},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-21 18:00:00"},{"dt":1613941200,"main":{"temp":273.26,"feels_like":269.6,"temp_min":273.26,"temp_max":273.26,"pressure":1032,"sea_level":1032,"grnd_level":1024,"humidity":95,"temp_kf":0},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"clouds":{"all":55},"wind":{"speed":2.27,"deg":176},"visibility":10000,"pop":0,"sys":{"pod":"d"},"dt_txt":"2021-02-21 21:00:00"},{"dt":1613952000,"main":{"temp":272.11,"feels_like":268.23,"temp_min":272.11,"temp_max":272.11,"pressure":1032,"sea_level":1032,"grnd_level":1023,"humidity":98,"temp_kf":0},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04n"}],"clouds":{"all":77},"wind":{"speed":2.45,"deg":152},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-22 00:00:00"},{"dt":1613962800,"main":{"temp":271.9,"feels_like":267.49,"temp_min":271.9,"temp_max":271.9,"pressure":1030,"sea_level":1030,"grnd_level":1022,"humidity":98,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13n"}],"clouds":{"all":100},"wind":{"speed":3.16,"deg":156},"visibility":9310,"pop":0.24,"snow":{"3h":0.18},"sys":{"pod":"n"},"dt_txt":"2021-02-22 03:00:00"},{"dt":1613973600,"main":{"temp":271.65,"feels_like":266.59,"temp_min":271.65,"temp_max":271.65,"pressure":1027,"sea_level":1027,"grnd_level":1019,"humidity":97,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":4.01,"deg":168},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-22 06:00:00"},{"dt":1613984400,"main":{"temp":271.06,"feels_like":265.73,"temp_min":271.06,"temp_max":271.06,"pressure":1023,"sea_level":1023,"grnd_level":1015,"humidity":97,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":100},"wind":{"speed":4.29,"deg":177},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-22 09:00:00"},{"dt":1613995200,"main":{"temp":273.55,"feels_like":268.17,"temp_min":273.55,"temp_max":273.55,"pressure":1019,"sea_level":1019,"grnd_level":1011,"humidity":95,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"clouds":{"all":100},"wind":{"speed":4.78,"deg":180},"visibility":10000,"pop":0.19,"sys":{"pod":"d"},"dt_txt":"2021-02-22 12:00:00"},{"dt":1614006000,"main":{"temp":275.03,"feels_like":269.53,"temp_min":275.03,"temp_max":275.03,"pressure":1016,"sea_level":1016,"grnd_level":1008,"humidity":98,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":{"all":100},"wind":{"speed":5.37,"deg":188},"visibility":10000,"pop":1,"rain":{"3h":2.76},"sys":{"pod":"d"},"dt_txt":"2021-02-22 15:00:00"},{"dt":1614016800,"main":{"temp":277.02,"feels_like":272.17,"temp_min":277.02,"temp_max":277.02,"pressure":1011,"sea_level":1011,"grnd_level":1003,"humidity":98,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":{"all":100},"wind":{"speed":4.94,"deg":210},"visibility":439,"pop":0.96,"rain":{"3h":0.78},"sys":{"pod":"d"},"dt_txt":"2021-02-22 18:00:00"},{"dt":1614027600,"main":{"temp":278.14,"feels_like":274.41,"temp_min":278.14,"temp_max":278.14,"pressure":1008,"sea_level":1008,"grnd_level":1001,"humidity":97,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":{"all":100},"wind":{"speed":3.59,"deg":234},"visibility":4318,"pop":0.48,"rain":{"3h":0.25},"sys":{"pod":"d"},"dt_txt":"2021-02-22 21:00:00"},{"dt":1614038400,"main":{"temp":275.14,"feels_like":270.97,"temp_min":275.14,"temp_max":275.14,"pressure":1010,"sea_level":1010,"grnd_level":1002,"humidity":89,"temp_kf":0},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],"clouds":{"all":85},"wind":{"speed":3.2,"deg":289},"visibility":10000,"pop":0.44,"sys":{"pod":"n"},"dt_txt":"2021-02-23 00:00:00"},{"dt":1614049200,"main":{"temp":272.39,"feels_like":268.39,"temp_min":272.39,"temp_max":272.39,"pressure":1012,"sea_level":1012,"grnd_level":1004,"humidity":97,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":2.64,"deg":289},"visibility":10000,"pop":0,"sys":{"pod":"n"},"dt_txt":"2021-02-23 03:00:00"}],"city":{"id":4771414,"name":"Manassas Park","coord":{"lat":38.784,"lon":-77.4697},"country":"US","population":14273,"timezone":-18000,"sunrise":1613563102,"sunset":1613602179}}"""
        parsedPayload =  Gson().fromJson(testPayload,ForcastResponse::class.java)

    }

    @Test
    fun testForcastResponse() {
        // GIVEN
        //val testPayload = AssetReader().loadResource(RESOURCE_WEATHER_RESULT)

        // THEN
        assertThat(parsedPayload).isNotNull()

        parsedPayload?.run {
            assertThat(cod).isNotNull()
            assertThat(cod).isEqualTo("200")
            assertThat(message).isNotEmpty()
            assertThat(list).isNotNull()
            assertThat(list.size).isGreaterThan(0)

        }

    }

    @Test
    fun testForcastModel() {
        parsedPayload?.list?.get(0)?.run {
            assertThat(dt).isNotNull()
            assertThat(dt_txt).isNotEmpty()
            assertThat(main).isNotNull()
            assertThat(weather).isNotNull()
            assertThat(weather?.size).isGreaterThan(0)
            assertThat(wind).isNotNull()
        }

    }

    @Test
    fun testForcastMainModel() {
        parsedPayload?.list?.get(0)?.main?.run {
            assertThat(temp).isNotNull()
            assertThat(feels_like).isNotEmpty()
            assertThat(temp_min).isNotNull()
            assertThat(temp_max).isNotNull()
            assertThat(sea_level).isNotNull()
        }

    }

    @Test
    fun testForcastWeatherModel() {
        parsedPayload?.list?.get(0)?.weather?.get(0)?.run {
            assertThat(id).isNotNull()
            assertThat(main).isNotEmpty()
            assertThat(description).isNotNull()
        }

    }

    @Test
    fun testForcastWindModel() {
        parsedPayload?.list?.get(0)?.wind?.run {
            assertThat(speed).isNotNull()
            assertThat(deg).isNotEmpty()
        }

    }
}