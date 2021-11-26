package com.example.check.configuration

import com.example.check.data.UserSimp
import com.example.check.data.UserTeste
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Backend{

    @GET("/user")
    fun teste(): Call<List<UserTeste>>

    @GET("/user")
    fun logarUsuarios(): Call<List<UserSimp>>

    @POST("/user")
    fun createUsuario(@Body usuario: UserSimp): Call<Void>
}