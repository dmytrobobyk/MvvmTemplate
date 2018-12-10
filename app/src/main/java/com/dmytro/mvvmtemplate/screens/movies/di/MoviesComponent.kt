package com.dmytro.mvvmtemplate.screens.movies.di

import dagger.BindsInstance
import dagger.Component
import com.dmytro.mvvmtemplate.common.di.ActivityScope
import com.dmytro.mvvmtemplate.di.AppComponent
import com.dmytro.mvvmtemplate.screens.movies.MoviesActivity

@ActivityScope
@Component(modules = [MoviesModule::class], dependencies = [AppComponent::class])
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