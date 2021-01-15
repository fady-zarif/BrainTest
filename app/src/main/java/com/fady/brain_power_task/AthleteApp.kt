package com.fady.brain_power_task

import android.app.Application
import com.fady.brain_power_task.di.AppModule
import com.fady.brain_power_task.di.AppModule.networkModule
import com.fady.brain_power_task.di.AppModule.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AthleteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AthleteApp)
            androidLogger()
            modules(listOf(networkModule,viewModule))
        }
    }
}