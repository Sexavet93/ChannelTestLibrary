package com.example.testlibrary

import android.content.Intent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {

    private val flutterToNativeChannelName = "com.example.flutter_to_native"
    private val nativeToFlutterChannelName = "com.example.native_to_flutter"

    companion object {
        lateinit var nativeToFlutterChannel: MethodChannel
    }

    private lateinit var flutterToNativeChannel: MethodChannel


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        flutterToNativeChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, flutterToNativeChannelName)
        nativeToFlutterChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, nativeToFlutterChannelName)

        flutterToNativeChannel.setMethodCallHandler { call, result ->
            when (call.method) {
                "showAndroidView" -> {
                    showAndroidView()
                    result.success("Native method called")
                }
                else -> {
                    result.notImplemented()
                }
            }
        }
    }

    private fun showAndroidView() {
        val intent = Intent(this@MainActivity, TestActivity::class.java)
        startActivity(intent)
    }
}


// static const platform = MethodChannel('com.example.flutter_to_native')
// Future<void> callNativeMethod() async {
//   try {
//     await platform.invokeMethod('showAndroidView');
//   } catch (e) {
//     print('Error calling native method: $e');
//   }
// }

// static const platform2 = MethodChannel('com.example.native_to_flutter')
// Future<void> setMethodCallHandler() async {
//   platform2.setMethodCallHandler((call){
//        if(call.method == 'navigateToPage') {
//           print(call.method);
//        }
//   });
// }