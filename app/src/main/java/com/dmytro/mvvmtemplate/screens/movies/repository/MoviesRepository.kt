package com.dmytro.mvvmtemplate.screens.movies.repository

import com.dmytro.mvvmtemplate.screens.movies.rest.response.MovieResponse
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(): Single<List<MovieResponse>>
}