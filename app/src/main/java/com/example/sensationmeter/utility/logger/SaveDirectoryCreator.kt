package com.example.sensationmeter.utility.logger

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.example.sensationmeter.R
import java.io.File

class SaveDirectoryCreator {
    fun makeSaveDirectory(context: Context) {
        val sdPath: String = Environment.getExternalStorageState()

        if (Environment.MEDIA_MOUNTED.equals(sdPath)) {
            val root: File = Environment.getExternalStorageDirectory()
            val directory = File(root.absolutePath + "/SensationData")

            if (!directory.exists()) {
                directory.mkdir()
            }
        } else {
            Toast.makeText(context, R.string.app_sd_card_error, Toast.LENGTH_LONG).show()
        }
    }
}