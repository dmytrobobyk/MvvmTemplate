package com.dmytro.mvvmtemplate.screens.movies.rest.response

import com.google.gson.annotations.SerializedName
import com.dmytro.mvvmtemplate.common.rest.response.BaseResponse

data class MoviesResponse(@SerializedName("results") val movies: List<MovieResponse>): BaseResponse() {
    override fun isResponseValid(): Boolean {
        return movies.isNotEmpty()
    }
}