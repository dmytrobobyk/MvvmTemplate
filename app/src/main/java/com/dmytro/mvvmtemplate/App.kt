package com.dmytro.mvvmtemplate

import android.support.multidex.MultiDexApplication
import com.dmytro.mvvmtemplate.di.AppModule
import com.dmytro.mvvmtemplate.di.DaggerAppComponent
import timber.log.Timber

class App : MultiDexApplication() {

    val component by lazy {
        DaggerAppComponent.builder()
                .context(this)
                .plus(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)

        Timber.plant(Timber.DebugTree())
    }
}