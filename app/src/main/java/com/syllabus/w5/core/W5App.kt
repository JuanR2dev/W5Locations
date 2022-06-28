package com.syllabus.w5.core

import android.app.Application
import com.syllabus.w5.BuildConfig
import com.syllabus.w5.di.apiModule
import com.syllabus.w5.di.dominosModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class W5App : Application() {

    override fun onCreate() {
        super.onCreate()
        // region Timber init
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        Timber.d("Timber planted")
        // endregion Timber init

        startKoin {
            printLogger(level = Level.ERROR)
            modules(apiModule, dominosModule)
        }

    }

}