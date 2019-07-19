package com.example.sensationmeter

import android.app.Application
import com.example.sensationmeter.dagger.AppComponent
import com.example.sensationmeter.dagger.AppModule
import com.example.sensationmeter.dagger.DaggerAppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var application: MainApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}