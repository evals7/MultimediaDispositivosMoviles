package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}



@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}


@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)) {

    //4. variable result remember para que la función almacene el objeto en la memoria
    var result by remember { mutableStateOf(1) }

    //5. variable inmutable para que, segun el resultado, aparezca una imagen u otra
    val imageResource = when (result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    //0. column() -> para que todos los elementos se muestren en columna
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally ){

        //1.image() para insertar el recurso imagen importado , las imagenes del dado. ContentDescription ofrece el resultado de forma accesible
        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        //2.spacer() para establecer un espacio entre la imagen y el botón
        Spacer(modifier = Modifier.height(16.dp))

        //3.button() debajo, con un texto stringRessource roll definido en el strings.xml. Al button le pasamos una función onClick() como parámetro, es una Lamda que contiene la variable result equiparada a random()
        Button(onClick = {
            result = (1..6).random()
        }){
            Text(stringResource(R.string.roll))
        }
    }
}