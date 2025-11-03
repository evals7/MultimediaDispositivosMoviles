package com.example.botonazo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textViewContador: TextView = findViewById(R.id.textViewContador)
        val buttonPulsar: Button = findViewById(R.id.buttonPulsar)
        var pulsaciones: Int= 0

        buttonPulsar.setOnClickListener {
            pulsaciones++
            println("Número de pulsaciones: $pulsaciones")
        }

        val editTextNumberPeso: EditText = findViewById(R.id.editTextNumberPeso)
        val editTextTextAltura: EditText = findViewById(R.id.editTextTextAltura)
        val buttonIMC: Button = findViewById(R.id.buttonIMC)
        val textViewCalculo: TextView = findViewById(R.id.textViewCalculo)

        buttonIMC.setOnClickListener{
            val peso = editTextNumberPeso.text.toString().toDouble()
            val altura =editTextTextAltura.text.toString().toDouble()
            val calculo = peso/ pow(altura, 2.0)
            textViewCalculo.setText(calculo.toString())
        }
    }
}