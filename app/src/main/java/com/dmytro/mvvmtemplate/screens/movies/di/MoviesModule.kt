package com.dmytro.mvvmtemplate.screens.movies.di

import dagger.Module
import dagger.Provides
import com.dmytro.mvvmtemplate.common.di.ActivityScope
import com.dmytro.mvvmtemplate.common.rest.createService
import com.dmytro.mvvmtemplate.common.rx.RxWorkers
import com.dmytro.mvvmtemplate.screens.movies.MoviesViewModel
import com.dmytro.mvvmtemplate.screens.movies.repository.MoviesLocalRepository
import com.dmytro.mvvmtemplate.screens.movies.repository.MoviesRepository
import com.dmytro.mvvmtemplate.screens.movies.rest.MoviesApi

@Module
class MoviesModule {

    @Provides
    @ActivityScope
    fun api() = createService(MoviesApi::class.java)

    @Provides
    @ActivityScope
    fun repository(api: MoviesApi): MoviesRepository = MoviesLocalRepository(api)

    @Provides
    @ActivityScope
    fun viewModel(repository: MoviesRepository, workers: RxWorkers) = MoviesViewModel(repository, workers)
}