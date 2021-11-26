package com.example.check.configuration

import android.app.Application
import android.os.Build
import android.telecom.Call
import androidx.annotation.RequiresApi
import com.example.check.data.UserSimp
import retrofit2.http.POST

class RepositoryUsuario (application: Application){

    private val mRemote = RetrofitClient.createService(Backend::class.java)

    fun createUsuario(usuario: UserSimp){
        mRemote.createUsuario(usuario)
    }
}