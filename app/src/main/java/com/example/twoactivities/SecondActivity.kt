package com.example.twoactivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File

class SecondActivity : ComponentActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        count = intent.getIntExtra("count", 0)

        setContent {
            DisplaySquare(count)
        }

        logLifecycle("second_activity_onCreate")
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("second_activity_onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("second_activity_onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("second_activity_onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("second_activity_onStop")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("second_activity_onRestart")
    }

    override fun onDestroy() {
        logLifecycle("second_activity_onDestroy")
        super.onDestroy()
    }


    @Composable
    fun DisplaySquare(count: Int) {
        val square = count * count
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Square of Count: $square")
            Button(onClick = {
                val intent = Intent(this@SecondActivity, MainActivity::class.java).apply {
                    putExtra("count", count)
                }
                startActivity(intent)
            }) {
                Text("Go to Main Activity")
            }
        }
    }

    private fun logLifecycle(event: String) {
        lifecycleScope.launch {
            val logFile = File(filesDir, "lifecycle_log.txt")
            logFile.appendText("$event: Count = ${count * count}\n")
            logFile.appendText("-------------------------------------\n")
        }
    }
}