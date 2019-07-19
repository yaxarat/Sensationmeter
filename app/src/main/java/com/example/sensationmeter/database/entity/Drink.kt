package com.example.sensationmeter.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink_log")
data class Drink(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val intakeVolume: Int,
    val sugar: Int,
    val caffeine: Int,
    val alcohol: Int,
    val carbonation: Int
)