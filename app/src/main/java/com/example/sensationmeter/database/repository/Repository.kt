package com.example.sensationmeter.database.repository

import android.annotation.SuppressLint
import com.example.sensationmeter.database.entity.Drink
import com.example.sensationmeter.database.entity.Sense
import com.example.sensationmeter.database.entity.Survey
import com.example.sensationmeter.database.entity.Void
import com.example.sensationmeter.database.repository.dao.DrinkDao
import com.example.sensationmeter.database.repository.dao.SenseDao
import com.example.sensationmeter.database.repository.dao.SurveyDao
import com.example.sensationmeter.database.repository.dao.VoidDao
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("CheckResult")
@Singleton
class Repository @Inject constructor(private val drinkDao: DrinkDao, private val surveyDao: SurveyDao, private val senseDao: SenseDao, private val voidDao: VoidDao) {

    fun logDrink(drink: Single<Drink>) {
        drink.subscribeOn(Schedulers.io()).subscribe { newDrink -> drinkDao.insert(newDrink) }
    }

    fun logSurvey(survey: Single<Survey>) {
        survey.subscribeOn(Schedulers.io()).subscribe { newSurvey -> surveyDao.insert(newSurvey) }
    }

    fun logSense(sense: Single<Sense>) {
        sense.subscribeOn(Schedulers.io()).subscribe { newSense -> senseDao.insert(newSense) }
    }

    fun logVoid(void: Single<Void>) {
        void.subscribeOn(Schedulers.io()).subscribe { newVoid -> voidDao.insert(newVoid) }
    }

    fun getDrink(): Single<List<Drink>> {
        return drinkDao.getAllDrinkLog()
    }

    fun getSurvey(): Single<List<Survey>> {
        return surveyDao.getAllSurveyLog()
    }

    fun getSense(): Single<List<Sense>> {
        return senseDao.getAllSenseLog()
    }

    fun getVoid(): Single<List<Void>> {
        return  voidDao.getAllVoidLog()
    }
}