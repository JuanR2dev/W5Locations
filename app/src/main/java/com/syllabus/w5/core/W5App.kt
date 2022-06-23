package com.syllabus.w5.core

import android.app.Application
import com.syllabus.w5.BuildConfig
import timber.log.Timber

class W5App : Application() {

    override fun onCreate() {
        super.onCreate()
        // region Timber init
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        Timber.d("Timber planted")
        // endregion Timber init
    }

}