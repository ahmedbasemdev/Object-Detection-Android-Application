package com.ahmedbasem.thanwey.ui.dialogs
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.k.objectdetection.databinding.DialogChooseModelBinding
import com.k.objectdetection.dialog.SharedPref
import com.k.objectdetection.general.HomeScreen

/*
A dialog is a small window that prompts the user to make a decision,
provide some additional information, and inform the user about some particular task

https://www.geeksforgeeks.org/how-to-create-dialog-with-custom-layout-in-android/
 */

class DialogChooseModel(context: Context): Dialog(context) {
    lateinit var binding: DialogChooseModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogChooseModelBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // clicking on old_model button
        binding.oldModelContainer.setOnClickListener {
            // set the value of sharedpreferences
            SharedPref(context).set_model("mobilenetv1.tflite")
            dismiss()
        }

        binding.newModelContainer.setOnClickListener {
            SharedPref(context).set_model("mobile_netmeta.tflite")
            dismiss()

        }
    }
}