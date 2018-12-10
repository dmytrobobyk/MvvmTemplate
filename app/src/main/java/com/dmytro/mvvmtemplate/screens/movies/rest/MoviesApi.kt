package com.dmytro.mvvmtemplate.screens.movies.rest

import com.dmytro.mvvmtemplate.screens.movies.rest.response.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("popular/")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MoviesResponse>
}