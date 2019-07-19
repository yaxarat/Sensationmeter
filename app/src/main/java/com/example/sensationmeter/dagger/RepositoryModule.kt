package com.example.sensationmeter.dagger

import android.app.Application
import androidx.room.Room
import com.example.sensationmeter.database.AppDatabase
import com.example.sensationmeter.database.repository.service.DrinkDao
import com.example.sensationmeter.database.repository.service.SurveyDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "log.db").build()
    }

    @Singleton
    @Provides
    fun providesDrinkDao(appDatabase: AppDatabase): DrinkDao {
        return  appDatabase.drinkDao()
    }

    @Singleton
    @Provides
    fun provideSurveyDao(appDatabase: AppDatabase): SurveyDao {
        return  appDatabase.surveyDao()
    }
}