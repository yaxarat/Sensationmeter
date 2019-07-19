package com.example.sensationmeter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sensationmeter.database.entity.Drink
import com.example.sensationmeter.database.entity.Survey
import com.example.sensationmeter.database.repository.service.DrinkDao
import com.example.sensationmeter.database.repository.service.SurveyDao

@Database(entities = [Drink::class, Survey::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
    abstract fun surveyDao(): SurveyDao
}