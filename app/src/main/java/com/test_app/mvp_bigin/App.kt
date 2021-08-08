package com.test_app.mvp_bigin

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    // Before when we'll used Dagger lib
    private val cicerone : Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
    override fun onCreate() {
        instance = this
        super.onCreate()
    }


    companion object {
         lateinit var instance : App
    }
}