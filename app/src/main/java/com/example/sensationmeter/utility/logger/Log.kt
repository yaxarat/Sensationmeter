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
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Single.zip
import io.reactivex.functions.BiFunction
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
    private var drinkList: List<Drink> = emptyList()
    private var senseList: List<Sense> = emptyList()
    private var surveyList: List<Survey> = emptyList()
    private var voidList: List<Void> = emptyList()

    private fun userIdCheck(): String {
        val folder = "SensationData/"
        return "$folder$userName.csv"
    }

    fun exportToCSV(): Boolean {
        checkHeader()
        fetchRecords()
//        makeEntry()
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

    // TODO: fix
    // Combine multilpe rx into one here
    private fun fetchRecords() {
        zip(
            repository.getDrink(),
            repository.getSense(),
            repository.getSurvey(),
            repository.getVoid(),
            Function4<List<Drink>, List<Sense>, List<Survey>, List<Void>, List<Any>> { drinks, senses, survey, void  -> return@Function4 printer(drinks, senses, survey, void) })
            .subscribeOn(Schedulers.computation())
            .subscribe { response -> makeEntry(response) }
    }
    private fun makeEntry(data: List<Any>) {
        Log.d("Tag", "${data[0]} \n ${data[1]} \n" +
                " ${data[2]} \n" +
                " ${data[3]}")
    }

    private fun printer(drinks: List<Drink>, senses: List<Sense>, surveys: List<Survey>, voids: List<Void>): List<Any> {
        return arrayListOf(drinks, senses, surveys, voids)
    }

    private fun write() {
        val fileWriter = FileWriter(log, true)
        val bufferWriter = BufferedWriter(fileWriter)
        bufferWriter.write("$entry\n")
        bufferWriter.close()
    }
}