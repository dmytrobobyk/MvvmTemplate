package com.dmytro.mvvmtemplate.screens.movies.di

import com.dmytro.mvvmtemplate.common.di.ActivityScope
import com.dmytro.mvvmtemplate.di.AppComponent
import com.dmytro.mvvmtemplate.screens.movies.MoviesActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MoviesModule::class])
interface MoviesComponent {
    fun inject(target: MoviesActivity)

    fun activity(): MoviesActivity

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: MoviesActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: MoviesModule): Builder

        fun build(): MoviesComponent
    }
}