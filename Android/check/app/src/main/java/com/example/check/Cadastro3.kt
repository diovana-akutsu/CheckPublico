package com.example.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.check.configuration.HttpRequest
import com.example.check.configuration.RepositoryUsuario
import com.example.check.data.UserSimp
import com.example.check.data.UserTeste
import com.example.check.viewModel.CadastroViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cadastro3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro3)
    }

    val http = HttpRequest.criar()

    fun voltarTelaCadastro2(v: View) {
        startActivity(Intent(this, Cadastro2::class.java))
    }

    fun cadastrar(v: View){

        val nome: String= intent.getStringExtra("usuario").toString()
        val edtEmail:EditText = findViewById(R.id.edt_email)
        val stringEmail: String = edtEmail.text.toString()
        val edtSenha:EditText = findViewById(R.id.edt_senha)
        val stringSenha:String = edtSenha.text.toString()

        var usuario = UserSimp(nome, stringEmail, stringSenha)

        http.createUsuario(usuario).enqueue(object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                Log.e("api", "usuário criado com sucesso")
                Toast.makeText(baseContext, "usuário criado com sucesso", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("api", t.message!!)
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

//    fun voltarTelaCadastro2(v: View) {
//
//    }
}