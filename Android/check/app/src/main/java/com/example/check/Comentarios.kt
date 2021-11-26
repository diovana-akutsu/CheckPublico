package com.example.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.check.configuration.HttpRequest
import com.example.check.data.UserTeste
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Comentarios : AppCompatActivity() {

    val http = HttpRequest.criar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentarios)
    }

    //TIRAR ISSO ERA SÓ PRA VER A TELAAAAAAAA
    fun irTelaCategoriaNotificacao(v: View) {
        startActivity(Intent(this, Cadastro1::class.java))
//        carregarTeste()
    }
    //TIRAR ISSO ERA SÓ PRA VER A TELAAAAAAAA

    fun carregarTeste(){
        http.teste().enqueue(object : Callback<List<UserTeste>> {
            override fun onResponse(
                call: Call<List<UserTeste>>,
                response: Response<List<UserTeste>>
            ) {
                Toast.makeText(baseContext, response.body()?.get(0).toString(), Toast.LENGTH_LONG).show();
            }

            override fun onFailure(call: Call<List<UserTeste>>, t: Throwable) {
                Log.e("api", t.message!!)
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}