package com.dmytro.mvvmtemplate.screens.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.dmytro.mvvmtemplate.App
import com.dmytro.mvvmtemplate.R
import com.dmytro.mvvmtemplate.common.BaseActivity
import com.dmytro.mvvmtemplate.common.rest.exceptions.NetworkException
import com.dmytro.mvvmtemplate.common.util.Resource
import com.dmytro.mvvmtemplate.common.viewmodel.ViewModelFactory
import com.dmytro.mvvmtemplate.screens.movies.adapter.MoviesAdapter
import com.dmytro.mvvmtemplate.screens.movies.di.DaggerMoviesComponent
import com.dmytro.mvvmtemplate.screens.movies.di.MoviesModule
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : BaseActivity() {

    private val component by lazy {
        DaggerMoviesComponent.builder()
                .appComponent((application as App).component)
                .activity(this)
                .plus(MoviesModule())
                .build()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MoviesViewModel>
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]


        initToolbar()
        setToolbarTitle(getString(R.string.app_name))
        initRecyclerView()
        initAdapter()

        viewModel.moviesList().observe(this, Observer {
            when (it?.status) {
                Resource.Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    moviesRecyclerView.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    (moviesRecyclerView.adapter as MoviesAdapter).replaceAll(it.data!!)
                    progressBar.visibility = View.GONE
                    moviesRecyclerView.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    moviesRecyclerView.visibility = View.VISIBLE
                    if (it.throwable is NetworkException) {
                        Snackbar.make(window.decorView, it.throwable.description!!, Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(window.decorView, it.throwable?.message.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                    viewModel.clearLiveData()
                }
            }
        })
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        moviesRecyclerView.layoutManager = gridLayoutManager
    }

    private fun initAdapter() {
        moviesRecyclerView.adapter = MoviesAdapter()
    }

    private fun initViews() {

    }


}