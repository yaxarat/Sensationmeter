package com.example.sensationmeter.dagger

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun providesApplication(): Application {
        return application
    }
}