package com.example.check.configuration

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpRequest {
    var contants: Constants = Constants();
    fun criar(): Backend {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        // Criamos um Builder do Retrofit (preparamos um conjunto de chamadas de EndPoints de uma API)
        val retrofit = Retrofit.Builder()
            .baseUrl(contants.IP_BACKEND)
            .addConverterFactory(GsonConverterFactory.create(gson)) // quem será o "conversor" JSON <=> Classe
            .client(okHttpClient)
            .build()

        // recuperamos a implementação da interface com os EndPoints
        val api = retrofit.create(Backend::class.java)

        return api
    }
}