package com.example.sensationmeter.dagger

import com.example.sensationmeter.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(drinkLogFragment: DrinkLogFragment)
    fun inject(meterDialogFragment: MeterDialogFragment)
    fun inject(meterFragment: MeterFragment)
    fun inject(voidLogFragment: VoidLogFragment)
    fun inject(settingFragment: SettingFragment)
}