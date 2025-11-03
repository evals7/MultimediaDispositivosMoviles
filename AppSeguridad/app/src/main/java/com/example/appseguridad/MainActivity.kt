package com.example.appseguridad

import android.R.attr.label
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompat
import com.example.appseguridad.ui.theme.AppSeguridadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppSeguridadTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyApp(){
    var edad by rememberSaveable{ //guarda el estado de la variable aun con rotaciones de pantalla
        mutableStateOf("")
    }
    var mensaje by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Puerta de acceso")
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            shape = MaterialTheme.shapes.large,
            color = Color(0xFF9FE5C0),
            shadowElevation = 10.dp
        ){
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Bienvenidos a la discoteca",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    )
                )
                Spacer(
                    Modifier.height(24.dp)
                )
                TextField(edad,
                    onValueChange = {edad = it.trim()},
                    label = {Text("Introduce tu edad: ")}
                )
                Spacer(
                    Modifier.height(24.dp)
                )
                Button(
                    onClick = {
                        val edad = edad.toIntOrNull()
                        mensaje = when{
                            edad == null -> "Edad no válida"
                            edad <18 -> "Menor de 18, no entras"
                            else -> "Pasa"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ){
                    Text("DNI")
                }
                Spacer(
                    Modifier.height(24.dp)
                )
                if (mensaje != null) {
                    Text(
                        text = mensaje!!,
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 30.sp
                        )
                    )
                }

            }
        }
    }
}

