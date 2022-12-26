package kg.erjan.musicplayer.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        PACKAGE_NAME = applicationContext.packageName
    }

    companion object{
        var PACKAGE_NAME = ""
    }
}