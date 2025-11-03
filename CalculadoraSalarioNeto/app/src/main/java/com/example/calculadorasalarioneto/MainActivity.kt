package com.example.calculadorasalarioneto

import android.R.attr.onClick
import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorasalarioneto.ui.theme.CalculadoraSalarioNetoTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraSalarioNetoTheme {
                var mostrarResultados by remember { mutableStateOf(false) }
                var brutoMensual by remember { mutableDoubleStateOf(0.0) }
                var retencionIrpf by remember { mutableDoubleStateOf(0.0) }
                var bonificacionHijos by remember { mutableDoubleStateOf(0.0) }
                var netoMensual by remember { mutableDoubleStateOf(0.0) }

                if (!mostrarResultados) {
                    IntroducirDatos(
                        calcular = { bruto, retencion, bonificacion, neto ->
                            mostrarResultados = true
                            brutoMensual = bruto
                            retencionIrpf = retencion
                            bonificacionHijos = bonificacion
                            netoMensual = neto
                        }
                    )
                } else {
                    MostrarResultados(
                        brutoMensual = brutoMensual,
                        retencionIrpf = retencionIrpf,
                        bonificacionHijos = bonificacionHijos,
                        netoMensual = netoMensual,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroducirDatos(
    modifier: Modifier = Modifier,
    calcular: (
        brutoMensual: Double,
        retencionIrpf: Double,
        bonificacionHijos: Double,
        netoMensual: Double) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "CALCULA TU SALARIO NETO",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }
            )
        },                      //barra superior
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    text="2025. All rights reserved"
                )
            }
        },                    //barra inferior
    ) { innerPadding ->
        val image = painterResource(R.drawable.imagen_finanzas) //variables de contenido
        var edad by remember {mutableStateOf("")}
        var grupo by remember {mutableStateOf("")}
        var discapacidad by remember {mutableStateOf("")}
        var estado by remember {mutableStateOf("")}
        var hijos by remember {mutableStateOf("")}
        var bruto by remember {mutableStateOf("")}
        var pagas by remember {mutableStateOf("")}
                                                                //estructura del campo central con textfields
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(Modifier.height(8.dp))
            Image(
                painter = image,
                contentDescription = "icono_finanzas",
                modifier = Modifier.size(100.dp)
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text= "Rellena los siguientes campos para calcular tu salario: ")
            Spacer(Modifier.height(8.dp))
            TextField(
                value = edad,
                onValueChange = {edad = it },
                label = {Text("Edad: ")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = grupo,
                onValueChange = {grupo = it },
                label = {Text("Grupo profesional: ")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = discapacidad,
                onValueChange = {discapacidad = it },
                label = {Text("Grado de discapacidad: ")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = estado,
                onValueChange = {estado = it },
                label = {Text("Estado civil: ")},
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = hijos,
                onValueChange = {hijos = it },
                label = {Text("Nº de hijos*: ")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = bruto,
                onValueChange = {bruto = it },
                label = {Text("Salario bruto anual*: ")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = pagas,
                onValueChange = {pagas = it },
                label = {Text("Número de pagas*: ")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(300.dp)
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                var h = hijos.toIntOrNull()
                var b = bruto.toDoubleOrNull()
                var p = pagas.toIntOrNull()

                if (h!=null && b!=null && p!=null){
                    val brutoMensual = b / p                //cálculo del salario bruto mensual
                    val retencionIrpf = brutoMensual*20/100         //cálculo del salario con una retención de un 20%
                    val bonificacionHijos = brutoMensual * (0.01 * h)       //cálculo de la bonificación según n1 de hijos
                    val netoMensual = brutoMensual - retencionIrpf + bonificacionHijos  //cálculo del neto mensual

                    //el resto de parámetros no sirven para el cálculo, así que los he ignorado

                    calcular(brutoMensual, retencionIrpf, bonificacionHijos, netoMensual)
                }
            }) {
                Text("Calcular")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarResultados(
    modifier: Modifier = Modifier,
    brutoMensual: Double,
    retencionIrpf: Double,
    bonificacionHijos: Double,
    netoMensual: Double
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "RESULTADOS",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    text = "2025. All rights reserved"
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant,),
                modifier = Modifier.size(width = 300.dp, height = 100.dp)
            ) {
                Text(
                    text = "Salario bruto mensual: ",
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    textAlign = TextAlign.Left,

                )
                Text(
                    text = "${String.format("%.2f", brutoMensual)} €",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant,),
                modifier = Modifier.size(width = 300.dp, height = 100.dp)

            ) {
                Text(
                    text = "Salario neto mensual: ",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = "${String.format("%.2f", netoMensual)} €",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant,),
                modifier = Modifier.size(width = 300.dp, height = 100.dp)
            ) {
                Text(
                    text = "Retención de IRPF mensual: ",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = "${String.format("%.2f", retencionIrpf)} €",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant,),
                modifier = Modifier.size(width = 300.dp, height = 100.dp)
            ) {
                Text(
                    text = "Posibles bonificaciones: ",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = "${String.format("%.2f", bonificacionHijos)} €",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
@Preview
fun VistaPrevia(){
    CalculadoraSalarioNetoTheme{
        IntroducirDatos(
            calcular = {_, _, _, _ -> }  //declarando una Lamda va´cía
        )
    }
}
@Composable
@Preview
fun VistaPrevia2(){
    CalculadoraSalarioNetoTheme{
        MostrarResultados(
            brutoMensual = 0.0, //declarando los parámetros a null
            retencionIrpf = 0.0,
            bonificacionHijos = 0.0,
            netoMensual = 0.0
        )
    }
}
