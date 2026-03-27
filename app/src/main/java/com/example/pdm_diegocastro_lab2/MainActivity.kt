package com.example.pdm_diegocastro_lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pdm_diegocastro_lab2.ui.theme.PDM_DiegoCastrolab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDM_DiegoCastrolab2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    PDM_DiegoCastrolab2Theme {
        Greeting("placeholder")
    }
}

@Preview(showBackground = true)
@Composable
fun App(modifier: Modifier = Modifier){
    val usuario: MutableState<String> = remember { mutableStateOf("") }
    val entries = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        TextField(
            value = usuario.value,
            onValueChange = { usuario.value = it },
            label = { Text("Nombre de usuario") }
        )

        Button(
            onClick = {
                if (usuario.value.isNotBlank()) {
                    entries.add(usuario.value)
                    usuario.value = ""
                }
            }
        ) {
            Text(text = "Guardar")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Listado de nombres y posicion en la lista")
            Button(
                onClick = {
                    entries.clear()
                }

            ) {
                Text(text = "Limpiar" )
            }
        }
        
        LazyColumn {
            itemsIndexed(entries) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item
                    )
                    Text(
                        text = (index + 1).toString()
                    )
                }
            }
        }
    }

}
