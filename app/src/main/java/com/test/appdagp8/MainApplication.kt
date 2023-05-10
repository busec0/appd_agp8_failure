package com.test.appdagp8

import android.app.Application
import android.util.Log


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        const val APPD_COLLECTOR_URL = "<REPLACEME>"
        const val APPD_API_KEY = "<REPLACEME>"

    }

}