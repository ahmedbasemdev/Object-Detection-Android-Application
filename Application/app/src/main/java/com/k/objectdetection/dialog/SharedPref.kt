package com.k.objectdetection.dialog

import android.content.Context


/*
Shared Preferences is the way in which one can store and retrieve small amounts of primitive data as key/value pairs
to a file on the device storage such as String, int, float, and Boolean.

Reference >> https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/

*/

class SharedPref(val context: Context) {

    companion object {
        const val SHARED_NAME = "userdata"
    }

    // Storing data into SharedPreferences
    val preference = context.getSharedPreferences(
        SHARED_NAME, Context.MODE_PRIVATE
    )

    fun set_model(name: String) {
        // Creating an Editor object to edit(write to the file)
        val editor = preference.edit()
        editor.putString("model", name)
        editor.apply()
    }

    fun get_model(): String {
        return preference.getString("model", "")!!
    }




}