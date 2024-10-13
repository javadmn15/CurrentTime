package com.apptick.currenttime.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CurrentTimeApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}