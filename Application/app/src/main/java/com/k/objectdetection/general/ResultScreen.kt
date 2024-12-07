/*
HELP TAKEN
https://developer.android.com/topic/libraries/view-binding
How to bind XML views in Code & then just copy paste the same code for 10 times.
As application support maximum 10 results at a time.

https://developer.android.com/courses/android-basics-kotlin/unit-1

Taken a Kotlin Course about how to start working with android basics.
*/

package com.k.objectdetection.general

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.k.objectdetection.databinding.ActivityResultScreenBinding

class ResultScreen : AppCompatActivity() {

    // calling binding class for xml
    // which is generated automatically.
    lateinit var resultBinding: ActivityResultScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            // inflating our xml layout in our activity main binding
            resultBinding = ActivityResultScreenBinding.inflate(layoutInflater)
            // getting our root layout in our view and set Content view for our layout.
            setContentView(resultBinding.root)

            // Receive all the values stored in Constants earlier during the object detection
            val receivedList = Constants.resultsArray


            // Bind the results to Buttons and assign on Click Listeners to open the Amazon Link
            // Repeat the same thing 10 times with all 10 buttons as we support up to 10 objects initially
            resultBinding.result0.setText(receivedList.get(0))
            resultBinding.result0.setOnClickListener {
                openBrowser(resultBinding.result0.text.toString())
            }
            resultBinding.result1.setText(receivedList.get(1))
            resultBinding.result1.setOnClickListener {
                openBrowser(resultBinding.result1.text.toString())
            }
            resultBinding.result2.setText(receivedList.get(2))
            resultBinding.result2.setOnClickListener {
                openBrowser(resultBinding.result2.text.toString())
            }
            resultBinding.result3.setText(receivedList.get(3))
            resultBinding.result3.setOnClickListener {
                openBrowser(resultBinding.result3.text.toString())
            }
            resultBinding.result4.setText(receivedList.get(4))
            resultBinding.result4.setOnClickListener {
                openBrowser(resultBinding.result4.text.toString())
            }
            resultBinding.result5.setText(receivedList.get(5))
            resultBinding.result5.setOnClickListener {
                openBrowser(resultBinding.result5.text.toString())
            }
            resultBinding.result6.setText(receivedList.get(6))
            resultBinding.result6.setOnClickListener {
                openBrowser(resultBinding.result6.text.toString())
            }
            resultBinding.result7.setText(receivedList.get(7))
            resultBinding.result7.setOnClickListener {
                openBrowser(resultBinding.result7.text.toString())
            }
            resultBinding.result8.setText(receivedList.get(8))
            resultBinding.result8.setOnClickListener {
                openBrowser(resultBinding.result8.text.toString())
            }
            resultBinding.result9.setText(receivedList.get(9))
            resultBinding.result9.setOnClickListener {
                openBrowser(resultBinding.result9.text.toString())
            }
        } catch (ex: Exception) {
            Log.e("Results Exception", ex.message.toString())
        }

    }

    // A fun to open the browser and pass the object name in amazon UK site link. It will open the link in Browser and browser will redirect to Amazon Application, if installed in the device.
    fun openBrowser(name: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.co.uk/s?k=" + name.replace(" " , "%20")))
        startActivity(browserIntent)
    }

    // A fun to close the activity and move back to Splash Screen.
    fun ExitScreen(v: View?) {
        startActivity(Intent(this@ResultScreen, SplashActivity::class.java))
    }

    // A fun to Clear all the data from results array and set null values to the buttons.
    fun ClearData(v: View?) {
        Constants.resultsArray.clear()
        resultBinding.result0.setText("")
        resultBinding.result1.setText("")
        resultBinding.result2.setText("")
        resultBinding.result3.setText("")
        resultBinding.result4.setText("")
        resultBinding.result5.setText("")
        resultBinding.result6.setText("")
        resultBinding.result7.setText("")
        resultBinding.result8.setText("")
        resultBinding.result9.setText("")

    }
}