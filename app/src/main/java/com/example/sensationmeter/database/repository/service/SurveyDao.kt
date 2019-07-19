package com.example.sensationmeter.database.repository.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sensationmeter.database.entity.Survey

@Dao
interface SurveyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(survey: Survey)
}