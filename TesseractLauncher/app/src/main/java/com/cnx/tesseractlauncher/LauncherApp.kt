package com.cnx.tesseractlauncher

import android.app.Application

val appInstance: LauncherApp by lazy { LauncherApp.appInstance!! }

class LauncherApp: Application() {

    companion object {

        var appInstance: LauncherApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}