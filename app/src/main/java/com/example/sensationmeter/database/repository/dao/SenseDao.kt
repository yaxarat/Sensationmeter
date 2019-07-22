package com.example.sensationmeter.database.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sensationmeter.database.entity.Sense
import io.reactivex.Single

@Dao
interface SenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(sense: Sense)

    @Query("SELECT * FROM sense_log")
    fun getAllSenseLog(): Single<List<Sense>>
}