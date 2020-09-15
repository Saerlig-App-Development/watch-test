package com.example.watch_test_flutter

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.widget.TextView
import com.example.watch_test_flutter.R
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.Wearable

class MainActivity : WearableActivity() {
    private val wearClient: DataClient by lazy { Wearable.getDataClient(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()
        Log.i("WearOS", "Initialized")

        wearClient.addListener { dataEventBuffer ->
            Log.i("WearOS", "got data")
            handleData(dataEventBuffer)
        }


    }

    private fun handleData(dataEventBuffer: DataEventBuffer) {
        dataEventBuffer.forEach {
            Log.i("WearOs", it.toString())
            val label = findViewById<TextView>(R.id.text)
            label.text = it.toString()
        }
    }
}
