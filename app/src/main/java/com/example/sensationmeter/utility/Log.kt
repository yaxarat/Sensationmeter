package com.example.sensationmeter.utility

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.sensationmeter.R
import com.example.sensationmeter.setting.UserInformation
import java.io.*

class Log(context: Context) {
    private val userName = UserInformation(context).getName()
    private val root: File = Environment.getExternalStorageDirectory()
    private var log: File = File(root, userIdCheck())
    private val csvHeader = "Date,Time,Sense Value,Tenseness,Tingling,Pressure,Pain,Other Sense,Intake Volume,Sugar,Caffeine,Alcohol,Carbonation,Void Volume,Location Label"
    private var entry = "No entry error"

    companion object {
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

    private fun userIdCheck(): String {
        val folder = "SensationData/"
        return "$folder$userName.csv"
    }

    fun makeEntry(data: Data): Boolean {
        entry = "${Watch.currentTime()}," +
                "${data.senseValue}," +
                "${data.tenseness}," +
                "${data.tingling}," +
                "${data.pressure}," +
                "${data.pain}," +
                "${data.otherSense}," +
                "${data.intakeVolume}," +
                "${data.sugar}," +
                "${data.caffeine}," +
                "${data.alcohol}," +
                "${data.carbonation}," +
                "${data.voidVolume}," +
                data.locationLabel
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
            //TODO: add proper error handling here. It only reports as a logcat at the moment. Maybe toast will be a good indicator of storage issue?
            Log.e("IOException", "IOException in Log.kt")
        }
    }

    private fun write() {
        val fileWriter = FileWriter(log, true)
        val bufferWriter = BufferedWriter(fileWriter)
        bufferWriter.write("$entry\n")
        bufferWriter.close()
    }
}