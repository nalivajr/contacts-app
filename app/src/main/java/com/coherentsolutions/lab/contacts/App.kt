package com.coherentsolutions.lab.contacts

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        _application = this
    }

    companion object {
        private var _application: Application? = null
        val application: Application
            get() = checkNotNull(_application)
    }
}