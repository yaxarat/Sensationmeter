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
import com.example.sensationmeter.utility.Watch.Companion.toDate
import com.example.sensationmeter.utility.Watch.Companion.format
import io.reactivex.Single
import io.reactivex.functions.Function4
import io.reactivex.schedulers.Schedulers
import java.io.*
import javax.inject.Inject

class Log @Inject constructor(private  val repository: Repository) {
    private val userName = UserInformation(MainApp.application).getName()
    private val root: File = Environment.getExternalStorageDirectory()
    private var log: File = File(root, userIdCheck())
    private val csvHeader = "Date,Time,Sense Value,Tenseness,Tingling,Pressure,Pain,Other Sense,Intake Volume,Sugar,Caffeine,Alcohol,Carbonation,Void Volume,Location Label"

    private fun userIdCheck(): String {
        val folder = "SensationData/"
        return "$folder$userName.csv"
    }

    fun exportToCSV(): Boolean {
        checkHeader()
        fetchRecords()
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

    @SuppressLint("CheckResult")
    private fun fetchRecords() {
        Single.zip(
            repository.getDrink().subscribeOn(Schedulers.newThread()),
            repository.getSense().subscribeOn(Schedulers.newThread()),
            repository.getSurvey().subscribeOn(Schedulers.newThread()),
            repository.getVoid().subscribeOn(Schedulers.newThread()),
            Function4 { s1: List<Drink>, s2: List<Sense>, s3: List<Survey>, s4: List<Void> ->  return@Function4 arrayListOf(s1, s2, s3, s4)})
            .subscribe { responseArray -> makeEntry(responseArray)}
    }

    @SuppressLint("NewApi")
    private fun makeEntry(data: List<List<Any>>) {
        var i = 0
        val rowSize = listOf(data[0], data[1], data[2], data[3]).flatten().size // TODO: issue is here
        val drinkList: List<Drink> = data[0].map { it as Drink }
        val senseList: List<Sense> = data[1].map { it as Sense }
        val surveyList: List<Survey> = data[2].map {it as Survey}
        val voidList: List<Void> = data[4].map { it as Void }

        Log.d("tag", "SIZE: $rowSize \n ------------------- \n $drinkList \n $senseList \n $surveyList \n $voidList \n -----------------------")

        do {
            var minTime = 0
            if (isBefore(senseList[0].time, drinkList[0].time)) {minTime = 1}
            if (isBefore(surveyList[0].time, senseList[0].time)) {minTime = 2}
            if (isBefore(voidList[0].time, surveyList[0].time)) {minTime = 3}

            when (minTime) {
                0 -> {write("${format(toDate(drinkList[0].time))},0,0,0,0,0,0,${drinkList[0].intakeVolume},${drinkList[0].sugar},${drinkList[0].caffeine},${drinkList[0].alcohol},${drinkList[0].carbonation}")}
                1 -> {write("${format(toDate(senseList[0].time))},${format(toDate(senseList[0].senseValue.toString()))},0,0,0,0,0,0,0,0,0,0,0,0,0,0")}
                2 -> {write("${format(toDate(surveyList[0].time))},0,${format(toDate(surveyList[0].tenseness.toString()))},${format(toDate(surveyList[0].tingling.toString()))},${format(toDate(surveyList[0].pressure.toString()))},${format(toDate(surveyList[0].pain.toString()))},${format(toDate(surveyList[0].otherSense.toString()))},0,0,0,0,0,0,0")}
                3 -> {write("${format(toDate(voidList[0].time))},0,0,0,0,0,0,0,0,0,0,0,${format(toDate(voidList[0].voidVolume.toString()))},${format(toDate(voidList[0].locationLabel))}")}
            }
            i++
        } while (i < rowSize)
    }

    @SuppressLint("NewApi")
    private fun isBefore(before: String, after: String): Boolean {
        return toDate(before).isBefore(toDate(after))
    }

    private fun write(entry: String) {
        var thisEntry = entry
        if (thisEntry.isBlank() || thisEntry.isEmpty()) { thisEntry = "No entry error" }
        val fileWriter = FileWriter(log, true)
        val bufferWriter = BufferedWriter(fileWriter)
        bufferWriter.write("$thisEntry\n")
        bufferWriter.close()
    }
}