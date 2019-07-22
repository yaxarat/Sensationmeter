package com.example.sensationmeter.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "survey_log")
data class Survey(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: String,
    val tenseness: Int,
    val tingling: Int,
    val pressure: Int,
    val pain: Int,
    val otherSense: Int
)