package com.example.check.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.example.check.configuration.RepositoryUsuario
import com.example.check.data.UserSimp

class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val mUsuarioRepository = RepositoryUsuario(application)

    fun createUsuario(usuario: UserSimp){
        mUsuarioRepository.createUsuario(usuario)
    }

}