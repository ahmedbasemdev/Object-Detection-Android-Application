/*
References

- View binding
makes it easier to write code that interacts with views
https://developer.android.com/topic/libraries/view-binding

*/

package com.k.objectdetection.general

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ahmedbasem.thanwey.ui.dialogs.DialogChooseModel
import com.k.objectdetection.R
import com.k.objectdetection.databinding.ActivityHomeScreenBinding
import com.k.objectdetection.databinding.ActivitySplashBinding
import com.k.objectdetection.dialog.SharedPref

class SplashActivity : Activity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)






        
    }

    fun StartDetection(v: View?) {
        startActivity(Intent(this@SplashActivity, HomeScreen::class.java))
    }


}