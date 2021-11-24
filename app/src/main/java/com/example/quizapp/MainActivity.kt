package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        val botonStar = findViewById<Button>(R.id.btn_start)
        val cajaTexto = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.et_name)

        botonStar.setOnClickListener{
            if (cajaTexto.text.toString().isEmpty()){
                Toast.makeText(this,"Por favor coloca tu nombre", Toast.LENGTH_SHORT).show()
            }else{
                //vamos a la otra plantilla
                val intent = Intent(this,QuizQuestionActivity::class.java)
                //le pasamos los datos al otro activity, el name y el value
                intent.putExtra(Constants.USER_NAME,cajaTexto.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}