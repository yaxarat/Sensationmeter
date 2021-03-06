package com.example.sensationmeter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sensationmeter.database.entity.Drink
import com.example.sensationmeter.database.entity.Sense
import com.example.sensationmeter.database.entity.Survey
import com.example.sensationmeter.database.entity.Void
import com.example.sensationmeter.database.repository.dao.DrinkDao
import com.example.sensationmeter.database.repository.dao.SenseDao
import com.example.sensationmeter.database.repository.dao.SurveyDao
import com.example.sensationmeter.database.repository.dao.VoidDao

@Database(entities = [Drink::class, Survey::class, Sense::class, Void::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
    abstract fun surveyDao(): SurveyDao
    abstract fun senseDao(): SenseDao
    abstract fun voidDao(): VoidDao
}