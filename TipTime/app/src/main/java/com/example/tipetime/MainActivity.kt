package com.example.tipetime

import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipetime.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //la variable Binding nos permite traer todos los botones sin buscar uno a uno
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener {
            calcularPropina()
        }

    }

    fun calcularPropina(){
        val textoImporte = binding.campoImporte.text.toString()     //traemos el campo y lo convertimos a string
        val importe = textoImporte.toDoubleOrNull()                 //parseamos

        val porcentaje =                                            //selección de porcentaje según el botón
            when (binding.grupoOpciones.checkedRadioButtonId){
                R.id.radioButton20 -> 0.20
                R.id.radioButton18 -> 0.18
                else -> 0.15
            }

        var propina = importe!! * porcentaje             //propina =importe * porcentaje (modo safe call por si es 0
        if(binding.interruptorRedondeo.isChecked){        //si el interruptor está marcado, propina ceil
            propina = ceil(propina)
        }


    binding.resultadoPropina.text = propina.toString()               //propina

    }
}

