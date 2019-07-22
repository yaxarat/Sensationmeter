package com.example.sensationmeter.utility.logger

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.sensationmeter.MainApp
import com.example.sensationmeter.R
import com.example.sensationmeter.setting.UserInformation
import com.example.sensationmeter.utility.Watch
import java.io.*

class Log {
    private val userName = UserInformation(MainApp.application).getName()
    private val root: File = Environment.getExternalStorageDirectory()
    private var log: File = File(root, userIdCheck())
    private val csvHeader = "Date,Time,Sense Value,Tenseness,Tingling,Pressure,Pain,Other Sense,Intake Volume,Sugar,Caffeine,Alcohol,Carbonation,Void Volume,Location Label"
    private var entry = "No entry error"

    private fun userIdCheck(): String {
        val folder = "SensationData/"
        return "$folder$userName.csv"
    }

    fun exportToCSV(): Boolean {

        checkHeader()
        write()
        return true
    }

    private fun checkHeader() {
        val fileWriter = FileWriter(log, true)
        val bufferWriter = BufferedWriter(fileWriter)
        try {
            val br = BufferedReader(FileReader(log))
            if (br.readLine() != csvHeader) {
                bufferWriter.write("$csvHeader\n")
                bufferWriter.close()
            }
            br.close()
        } catch (e: IOException) {
            Log.e("tag", "IOException")
        }
    }

    private fun write() {
        val fileWriter = FileWriter(log, true)
        val bufferWriter = BufferedWriter(fileWriter)
        bufferWriter.write("$entry\n")
        bufferWriter.close()
    }
}