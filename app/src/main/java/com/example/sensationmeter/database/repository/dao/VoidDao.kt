package com.example.sensationmeter.database.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sensationmeter.database.entity.Void
import io.reactivex.Single

@Dao
interface VoidDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(void: Void)

    @Query("SELECT * FROM drink_log")
    fun getAllVoidLog(): Single<List<Void>>
}