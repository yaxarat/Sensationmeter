package com.example.sensationmeter.dagger

import com.example.sensationmeter.DrinkLogFragment
import com.example.sensationmeter.MeterDialogFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(drinkLogFragment: DrinkLogFragment)
    fun inject(meterDialogFragment: MeterDialogFragment)
}