package com.example.sensationmeter.setting

import android.content.Context

class ApplicationSetting(context: Context) {
    private val masterPassword = "kejKsZ4QDf1438CnID7sFyeNp9dkdW0WDTdMQuR+z1c="
    private val preferencePassword = "vSU1F77ydXl53O5om1CT0Q=="
    private val sensationValue = "Value"

    private val preference = context.getSharedPreferences(preferencePassword, Context.MODE_PRIVATE)

    fun getMasterPassword(): String {
        return masterPassword
    }

    fun getPassword(): String {
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        return preference.getString(preferencePassword, "vSU1F77ydXl53O5om1CT0Q==")
    }

    fun setPassword(password: String) {
        val editor = preference.edit()
        editor.putString(preferencePassword, AESEncryption.encrypt(password))
        editor.apply()
    }

    fun getSensationVal(): Int {
        return preference.getInt(sensationValue, 0)
    }

    fun setSensationVal(value: Int) {
        val editor = preference.edit()
        editor.putInt(sensationValue, value)
        editor.apply()
    }
}