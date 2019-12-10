package com.zero.visitnepal

import android.app.Application
import com.zero.visitnepal.dagger.AppComponent
import com.zero.visitnepal.dagger.AppModule
import com.zero.visitnepal.dagger.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger()
        Timber.plant(Timber.DebugTree())
    }

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule())
            .build()
}