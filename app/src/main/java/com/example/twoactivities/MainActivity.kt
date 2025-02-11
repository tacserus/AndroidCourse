package com.example.twoactivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : ComponentActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        count = intent.getIntExtra("count", 0)

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0) + 1
        }

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Count: $count")
                Button(onClick = {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java).apply {
                        putExtra("count", count)
                    }
                    startActivity(intent)
                }) {
                    Text("Go to Second Activity")
                }
            }
        }

        logLifecycle("main_activity_onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("main_activity_onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("main_activity_onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("main_activity_onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("main_activity_onStop")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("main_activity_onRestart")
    }

    override fun onDestroy() {
        logLifecycle("main_activity_onDestroy")
        super.onDestroy()
    }

    private fun logLifecycle(event: String) {
        lifecycleScope.launch {
            val logFile = File(filesDir, "lifecycle_log.txt")
            logFile.appendText("$event: Count = $count\n")
            logFile.appendText("-------------------------------------\n")
        }
    }
}