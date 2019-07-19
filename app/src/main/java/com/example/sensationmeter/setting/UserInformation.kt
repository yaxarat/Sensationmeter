package com.example.sensationmeter.setting

import android.content.Context

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserInformation(context: Context) {
    private val preferenceName = "User_Name_Not_Set"

    private val preference = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)!!

    fun getName(): String {
        return preference.getString(preferenceName, "User_Name_Not_Set")
    }

    fun setName(name: String) {
        val editor = preference.edit()
        editor.putString(preferenceName, name)
        editor.apply()
    }
}