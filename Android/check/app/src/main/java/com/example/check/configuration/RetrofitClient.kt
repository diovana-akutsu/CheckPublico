package com.example.check.configuration

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*

class RetrofitClient private constructor() {

    companion object {

        private lateinit var retrofit: Retrofit
        private val baseUrl = "http://52.73.218.16/"

        @RequiresApi(Build.VERSION_CODES.O)
        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()

            val builder = GsonBuilder()
            builder.registerTypeAdapter(ByteArray::class.java,
                JsonSerializer { src: ByteArray?, typeOfSrc: Type?, context: JsonSerializationContext? ->
                    JsonPrimitive(
                        Base64.getEncoder().encodeToString(src)
                    )
                } as JsonSerializer<ByteArray?>)
            builder.registerTypeAdapter(ByteArray::class.java,
                JsonDeserializer<ByteArray> { json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext? ->
                    Base64.getDecoder().decode(json.asString)
                } as JsonDeserializer<ByteArray>)
            val gson = builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun <T> createService(serviceClass: Class<T>): T{
            return  getRetrofitInstance().create(serviceClass)
        }
    }
}