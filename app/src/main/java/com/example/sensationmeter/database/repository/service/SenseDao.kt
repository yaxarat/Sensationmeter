package com.example.sensationmeter.database.repository.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sensationmeter.database.entity.Sense

@Dao
interface SenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(sense: Sense)
}