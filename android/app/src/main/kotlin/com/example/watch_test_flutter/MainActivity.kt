package com.example.watch_test_flutter

import android.util.Log
import androidx.annotation.NonNull
import com.google.android.gms.tasks.Task
import com.google.android.gms.wearable.*
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "watchos"

    private val nodeClient: NodeClient by lazy { Wearable.getNodeClient(this)}
    private val dataClient: DataClient by lazy { Wearable.getDataClient(this)}
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.i("Home","finished config")
        // startWearApp()

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
            Log.i("Home",call.toString())
            sendMessageToClock()

        }
    }

    private fun startWearApp() {
        nodeClient.connectedNodes.addOnCompleteListener {
            if (it.isSuccessful) it.result?.forEach { node ->
                Log.wtf("INSPECT", "For node: $node")
                sendMessageToClock()
            }

            else Log.e("INSPECT", it.exception?.message ?: "", it.exception)
        }

    }

    private fun sendMessageToClock() {
        Log.i("Home","Sending message")


        val putDataReq: PutDataRequest = PutDataMapRequest.create("/count")
                .apply {dataMap.putLong("Time",System.currentTimeMillis()); }
                .asPutDataRequest()
                .setUrgent()

        val putDataTask: Task<DataItem> = dataClient.putDataItem(putDataReq)


        putDataTask.addOnCompleteListener {
            val text = when {
                it.isCanceled -> "Canceled"
                it.isSuccessful -> "Success"
                else -> "Fail: ${it.exception?.message}"
            }
            Log.i("wear", text)

        }

    }
}
