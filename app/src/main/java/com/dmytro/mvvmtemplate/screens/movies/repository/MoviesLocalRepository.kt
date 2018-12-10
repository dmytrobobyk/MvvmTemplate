package com.dmytro.mvvmtemplate.screens.movies.repository

import com.dmytro.mvvmtemplate.BuildConfig
import com.dmytro.mvvmtemplate.screens.movies.rest.MoviesApi
import com.dmytro.mvvmtemplate.screens.movies.rest.response.MovieResponse
import io.reactivex.Single

class MoviesLocalRepository(private val api: MoviesApi) : MoviesRepository {

    override fun getMovies(): Single<List<MovieResponse>> {
        return api.getPopularMovies(BuildConfig.API_KEY).map { it.movies }
    }
}