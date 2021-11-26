package com.example.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.check.data.UserSimp

class Cadastro2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro2)
    }

    fun irTelaCadastro3(v: View) {
        val nome: String= intent.getStringExtra("usuario").toString()

        Toast.makeText(baseContext,nome, Toast.LENGTH_LONG).show()

        var usuario = UserSimp(nome, "", "")

        val intent = Intent(this, Cadastro3::class.java)
        intent.putExtra("usuario", usuario.nome)

        startActivity(intent)
    }

    fun voltarTelaCadastro1(v: View) {
        startActivity(Intent(this, Cadastro1::class.java))
    }
}