package com.example.pdm_diegocastro_lab2.ui.screens

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes : NavKey {

    @Serializable
    data object Home : com.pdm0126.p1_resources.ui.screens.Routes

    @Serializable
    data object Screen1 : com.pdm0126.p1_resources.ui.screens.Routes


}