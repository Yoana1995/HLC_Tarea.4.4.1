package com.example.sensor_proximidad

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener{

    private lateinit var sensorManager: SensorManager
    private var mProximity: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if(sensorManager != null){
            mProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            if (mProximity!=null){
                sensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL)
            }
        }
    }


    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_PROXIMITY){
            val distance = event.values[0]
            val resul = findViewById<TextView>(R.id.txtValor)
            if (distance>0){
                resul.text = "Valor del sensor: "+distance+"\nEl objeto está lejos"
            }else{
                resul.text ="Valor del sensor: "+distance+"\nEl objeto está cerca"
            }
        }

    }

}