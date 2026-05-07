package com.example.pdm_diegocastro_lab2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.pdm_diegocastro_lab2.ui.screens.HomeScreen

import com.example.pdm_diegocastro_lab2.ui.screens.ListScreen
import com.example.pdm_diegocastro_lab2.ui.screens.Routes
import com.example.pdm_diegocastro_lab2.ui.screens.SensorScreen

@Composable
fun App(modifier: Modifier = Modifier){
    //1. creamos el backstack iniciando en home
    val backStack = rememberNavBackStack(Routes.HomeScreen)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        //topBar = { MainTopBar(title = "Nav3 App") },

        ) { innerPadding ->

        //2. NavDisplay es el contenedor que renderiza las pantallas
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = { backStack.removeLastOrNull() }, //maneja boton de atras

            entryProvider = entryProvider{
                //3. Mapear cada objeto de Routes.kt a un composable
                entry<Routes.HomeScreen> {
                    HomeScreen(
                        onNavigateToList = { backStack.add(Routes.ListScreen) },
                        onNavigateToSensor = { backStack.add(Routes.SensorScreen) }
                    )
                }

                entry<Routes.ListScreen> {
                    ListScreen(onBack = { backStack.removeLastOrNull() })
                }

                entry<Routes.SensorScreen> {
                    SensorScreen(onBack = { backStack.removeLastOrNull() })
                }

            }
        )
    }

}
