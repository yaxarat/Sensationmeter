package com.example.sensationmeter.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sense_log")
data class Sense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val senseValue: Int
)