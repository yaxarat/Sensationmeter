package com.example.sensationmeter.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "void_log")
data class Void(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: String,
    val voidVolume: Int,
    val locationLabel: String
)