package com.example.pdm_diegocastro_lab2.ui.screens

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes: NavKey {

    @Serializable
    data object HomeScreen : Routes

    @Serializable
    data object ListScreen : Routes

    @Serializable
    data object SensorScreen : Routes
}