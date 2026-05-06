package com.example.pdm_diegocastro_lab2.ui.screens

import androidx.compose.foundation.layout.WindowInsets
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object HomeScreen : Routes

    @Serializable
    data object ListScreen : Routes

    @Serializable
    data object SensorScreen : Routes
}