package com.example.sensationmeter.database.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sensationmeter.database.entity.Survey
import io.reactivex.Single

@Dao
interface SurveyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(survey: Survey)

    @Query("SELECT * FROM survey_log")
    fun getAllSurveyLog(): Single<List<Survey>>
}