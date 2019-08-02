package com.example.sensationmeter.utility.logger

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import com.example.sensationmeter.MainApp
import com.example.sensationmeter.database.entity.Drink
import com.example.sensationmeter.database.entity.Sense
import com.example.sensationmeter.database.entity.Survey
import com.example.sensationmeter.database.entity.Void
import com.example.sensationmeter.database.repository.Repository
import com.example.sensationmeter.setting.UserInformation
import io.reactivex.Single
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import java.io.*
import javax.inject.Inject

@SuppressLint("CheckResult")
class Log @Inject constructor(private  val repository: Repository) {
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
        fetchRecords()
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

    private fun fetchRecords() {
        Single.zip(
            repository.getDrink().subscribeOn(Schedulers.newThread()),
            repository.getSense().subscribeOn(Schedulers.newThread()),
            repository.getSurvey().subscribeOn(Schedulers.newThread()),
            repository.getVoid().subscribeOn(Schedulers.newThread()),
            Function4 { s1: List<Drink>, s2: List<Sense>, s3: List<Survey>, s4: List<Void> ->  return@Function4 arrayListOf(s1, s2, s3, s4)})
            .subscribe({responseArray -> makeEntry(responseArray)}, {error -> Log.e("tag", "$error")})
    }

    private fun makeEntry(data: List<List<Any>>) {
        var i = 0
        val drinkList: List<Drink> = data[0].map { it as Drink }
        val senseList: List<Sense> = data[1].map { it as Sense }
        val surveyList: List<Survey> = data[2].map {it as Survey}
        val voidList: List<Void> = data[4].map { it as Void }
        val rowSize = arrayListOf(drinkList.size, senseList.size, surveyList.size, voidList.size).sum()

        do {
            drinkList[0].time

            i++
        } while (i < rowSize)
    }

    private fun write() {
        val fileWriter = FileWriter(log, true)
        val bufferWriter = BufferedWriter(fileWriter)
        bufferWriter.write("$entry\n")
        bufferWriter.close()
    }
}
