package com.dmytro.mvvmtemplate.screens.movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.dmytro.mvvmtemplate.common.rx.RxWorkers
import com.dmytro.mvvmtemplate.common.util.Resource
import com.dmytro.mvvmtemplate.common.util.composeWith
import com.dmytro.mvvmtemplate.common.viewmodel.BaseViewModel
import com.dmytro.mvvmtemplate.screens.movies.repository.MoviesRepository
import com.dmytro.mvvmtemplate.screens.movies.rest.response.MovieResponse
import timber.log.Timber
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val repository: MoviesRepository,
                                          private val workers: RxWorkers) : BaseViewModel() {

    private val liveDataMovies: MutableLiveData<Resource<List<MovieResponse>>> by lazy {
        MutableLiveData<Resource<List<MovieResponse>>>()
    }

    fun moviesList(): LiveData<Resource<List<MovieResponse>>> {
        return liveDataMovies.also {
            if (it.value?.data == null) {
                getMovies()
            }
        }
    }

    fun getMovies() {
        addSubscription(repository.getMovies()
                .composeWith(workers)
                .doOnSubscribe {
                    liveDataMovies.value = Resource.loading()
                }
                .subscribe({
                    liveDataMovies.value = Resource.success(it)
                }, {
                    Timber.e(it)
                    liveDataMovies.value = Resource.error(it, liveDataMovies.value?.data)
                }))
    }

    fun clearLiveData() {
        liveDataMovies.value = Resource.restore()
    }
}