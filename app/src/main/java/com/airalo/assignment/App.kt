package com.airalo.assignment

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @Volatile
        var instance: App? = null
        fun getAppContext(): Context {
            return instance as Context
        }
    }
}
