package com.diary

import android.app.Application
import timber.log.Timber

class DiaryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
