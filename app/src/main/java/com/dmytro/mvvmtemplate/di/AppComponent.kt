package com.dmytro.mvvmtemplate.di

import dagger.BindsInstance
import dagger.Component
import com.dmytro.mvvmtemplate.App
import com.dmytro.mvvmtemplate.common.rx.RxWorkers
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: App)

    fun app(): App

    fun workers(): RxWorkers

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(app: App): Builder

        fun plus(module: AppModule): Builder

        fun build(): AppComponent
    }
}