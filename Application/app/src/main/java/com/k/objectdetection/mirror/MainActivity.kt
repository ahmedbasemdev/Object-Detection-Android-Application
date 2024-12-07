/*
HELP TAKEN
https://www.tensorflow.org/lite/examples/object_detection/overview
Downloaded the Tensorflow Software Development Kit and optimized for my use.
*/

package com.k.objectdetection.mirror

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.k.objectdetection.databinding.ActivityMainBinding
import com.k.objectdetection.general.ResultScreen

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(activityMainBinding.root)
        } catch (ex: Exception) {
            Log.e("Main Exception", ex.toString())
        }
    }

    // MODIFICATION MSG: Added a new function to show results.
    fun ShowResults(v: View?) {
        startActivity(
            Intent(
                this@MainActivity,
                ResultScreen::class.java
            )
        )
    }


    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            // Workaround for Android Q memory leak issue in IRequestFinishCallback$Stub.
            // (https://issuetracker.google.com/issues/139738913)
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }
}
