package com.example.testlibrary

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getView())
    }

    private fun getView(): View {
        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            gravity = Gravity.CENTER
            setVerticalGravity(Gravity.CENTER)
        }

        val buttonList = List(3) { index ->
            Button(this).apply {
                text = "Button $index"
                tag = index
                setBackgroundColor(Color.RED)
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                    marginStart = 16
                    marginEnd = 16
                }
                setOnClickListener {
                    MainActivity.nativeToFlutterChannel.invokeMethod("navigateToPage",tag)
                    finish()
                }
            }
        }

        buttonList.forEach { linearLayout.addView(it) }

        return linearLayout
    }
}