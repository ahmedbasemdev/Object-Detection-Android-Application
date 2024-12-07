/*
HELP TAKEN
https://www.tensorflow.org/lite/examples/object_detection/overview

Started with simple upload image detection and then move to Mirror Detection.
*/


package com.k.objectdetection.simple

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.k.objectdetection.databinding.ActivitySimpleDetectionBinding
import com.k.objectdetection.general.ResultScreen

class SimpleDetection : AppCompatActivity() {

    private lateinit var activitySimpleDetectionBinding: ActivitySimpleDetectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            activitySimpleDetectionBinding = ActivitySimpleDetectionBinding.inflate(layoutInflater)
            setContentView(activitySimpleDetectionBinding.root)
        } catch (ex: Exception) {
            Log.e("Main Exception", ex.toString())
        }
    }


    fun ShowResults(v: View?) {
        startActivity(
            Intent(
                this@SimpleDetection,
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
