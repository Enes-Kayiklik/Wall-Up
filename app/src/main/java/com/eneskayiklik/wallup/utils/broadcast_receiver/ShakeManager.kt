package com.eneskayiklik.wallup.utils.broadcast_receiver

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShakeManager(
    systemAction: String,
    onServiceAction: () -> Unit
) {
    val context = LocalContext.current
    DisposableEffect(context, systemAction) {
        var acceleration = 0F
        var currentAcceleration = 0F
        var lastAcceleration = 0F
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                lastAcceleration = currentAcceleration
                currentAcceleration = kotlin.math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
                val delta: Float = currentAcceleration - lastAcceleration
                acceleration = acceleration * 0.9F + delta
                if (acceleration > 12) {
                    onServiceAction()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
        }

        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )

        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }
}