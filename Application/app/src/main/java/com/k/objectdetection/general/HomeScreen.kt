/*
HELP TAKEN
https://developer.android.com/courses/android-basics-kotlin/unit-1

Taken a Kotlin Course about how to start working with android basics.

How to show dialog
https://www.geeksforgeeks.org/how-to-create-dialog-with-custom-layout-in-android/
*/

package com.k.objectdetection.general

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ahmedbasem.thanwey.ui.dialogs.DialogChooseModel
import com.k.objectdetection.mirror.MainActivity
import com.k.objectdetection.R
import com.k.objectdetection.databinding.ActivityHomeScreenBinding
import com.k.objectdetection.dialog.SharedPref

class HomeScreen : Activity() {
    // calling binding class for xml
    // which is generated automatically.
    lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inflating our xml layout in our activity main binding
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        // getting our root layout in our view and set Content view for our layout.
        setContentView(binding.root)


        buttonsClicks()

        checkModel()

        dialog()


    }

    private fun dialog() {
        if (SharedPref(applicationContext).get_model() == "") {
            val dialog = DialogChooseModel(this@HomeScreen)
            dialog.window?.setBackgroundDrawableResource(R.color.transparent)
            dialog.setCancelable(false)
            dialog.show()
            dialog.setOnDismissListener {
                checkModel()
            }
        }
    }

    // A function to check current model name
    private fun checkModel() {
        if (SharedPref(applicationContext).get_model() == "mobilenetv1.tflite") {
            binding.modelType.text = "Pre-trained"
        } else {
            binding.modelType.text = "trained"
        }
    }

    // a function contains all button operations in current class
    private fun buttonsClicks() {
        binding.changeModel.setOnClickListener {
            val dialog = DialogChooseModel(this@HomeScreen)
            dialog.window?.setBackgroundDrawableResource(R.color.transparent)
            dialog.show()
            dialog.setOnDismissListener {
                checkModel()
            }

        }
    }

    // A function to open the Mirror Detection Main Class Activity.
    fun MirrorDetection(v: View?) {
        startActivity(Intent(this@HomeScreen, MainActivity::class.java))
    }

    // A function to open the Simple Detection Activity.
    fun ImageDetection(v: View?) {
        startActivity(
            Intent(
                this@HomeScreen,
                com.k.objectdetection.simple.SimpleDetection::class.java
            )
        )
    }
}