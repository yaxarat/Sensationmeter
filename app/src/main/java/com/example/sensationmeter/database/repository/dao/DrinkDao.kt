package com.example.sensationmeter.database.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sensationmeter.database.entity.Drink
import io.reactivex.Single

@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(drink: Drink)

    @Query("SELECT * FROM drink_log")
    fun getAllDrinkLog(): Single<List<Drink>>
}