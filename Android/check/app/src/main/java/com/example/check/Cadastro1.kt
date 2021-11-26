package com.example.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.check.data.UserSimp
import com.example.check.data.UserTeste
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cadastro1 : AppCompatActivity() {

    lateinit var editNome: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro1)
        editNome = findViewById(R.id.edt_nome)
    }

    private var nomeCompleto:String = ""

    fun voltarLogin(v: View){
        startActivity(Intent(this, Comentarios::class.java))
    }

    fun irTelaCadastro2(v: View) {
        editNome = findViewById(R.id.edt_nome)
        val editSobrenome: EditText = findViewById(R.id.edt_sobrenome)

        nomeCompleto = editNome.text.toString() + " " + editSobrenome.text.toString()

        var usuario = UserSimp(nomeCompleto, "", "")

        val intent = Intent(this, Cadastro2::class.java)
        intent.putExtra("usuario", usuario.nome)

        startActivity(intent)
    }
}