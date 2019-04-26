package io.github.pdrum.hw2

import android.app.Application
import io.github.pdrum.hw2.inject.AppComponent
import io.github.pdrum.hw2.inject.DaggerAppComponent

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var injector: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAppComponent
            .builder()
            .build()
    }

}