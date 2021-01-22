package com.hogwarts.weatherapp.network
import com.github.simonpercic.oklog3.OkLogInterceptor
import com.hogwarts.weatherapp.models.LocationWeather
import com.hogwarts.weatherapp.models.WiFi
import com.hogwarts.weatherapp.network.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceManager {
    private val headers = HashMap<String, String>()
    private val uriPattern = "https://api.openweathermap.org/data/2.5/weather"
    val url = "https://api.openweathermap.org/data/2.5/weather"
    private val key = "ac8112a61cde5064a3cffb756e6195f7"
//            "${addresses[0].postalCode},${addresses[0].countryCode}&appid=$API_KEY"

    private fun getHttpClient(): OkHttpClient {
        return getHttpClientBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    private fun getHttpClientBuilder(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClientBuilder.addInterceptor(LoggingInterceptor())
        httpClientBuilder.addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
        httpClientBuilder.addInterceptor(
            OkLogInterceptor.builder()
                .withRequestHeaders(true)
                .withRequestBody(true)
                .withRequestBodyState(true)
                .withResponseHeaders(true)
                .withResponseBodyState(true)
                .withRequestContentType(true)
                .withRequestContentLength(true)
                .withResponseMessage(true)
                .withAllLogData()
                .build()
        )
        return httpClientBuilder
    }

    private fun getService(): Routes {
        return getRetrofit().create(Routes::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(uriPattern)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun setAuthToken(_token: String){
        headers["Accept"] = "application/json"
        headers["Authorization"] = "JWT $_token"
    }

    fun postWifi(wifi: WiFi): Call<ResponseBody> {
        return getService().postWifi(headers, wifi)
    }

    fun postWifiList(wifi: List<WiFi>): Call<ResponseBody> {
        return getService().postWifiList(headers, wifi)
    }

    fun getWeather(location:String): Call<LocationWeather> {
        return getService().getWeather(headers, location, key)
    }

}