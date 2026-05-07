package com.example.pdm_diegocastro_lab2.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun useSensor(sensorType: Int): List<Float> {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val sensor = sensorManager.getDefaultSensor(sensorType) ?: return emptyList()
    var sensorValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }

    DisposableEffect(sensorType) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.values?.let {
                    sensorValues = it.toList()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return sensorValues
}

@Composable
fun SensorScreen(onBack: () -> Unit, modifier: Modifier = Modifier) {
    val proximityValues = useSensor(Sensor.TYPE_PROXIMITY)

    val distance = proximityValues.firstOrNull() ?: 0f

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sensor de Proximidad", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        if (proximityValues.isNotEmpty()) {
            Text(text = "Distancia: $distance cm", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(16.dp))

            if (distance < 5) {
                Text(text = "ESTADO: CERCA", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold , color= Color.Red)
            } else {
                Text(text = "ESTADO: LEJOS", fontSize = 20.sp, color = Color.Green, fontWeight = FontWeight.ExtraBold)
            }
        } else {
            Text(
                text = "Sensor no disponible en este dispositivo",
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}
