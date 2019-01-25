package com.dmytro.mvvmtemplate.di

import android.content.Context
import com.dmytro.mvvmtemplate.common.data.TokenLocalStorage
import com.dmytro.mvvmtemplate.common.data.TokenStorage
import dagger.Module
import dagger.Provides
import com.dmytro.mvvmtemplate.common.rx.RxWorkers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun context(): Context = context

    @Provides
    @Singleton
    fun workers() = RxWorkers(Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    @Singleton
    fun token(context: Context): TokenStorage = TokenLocalStorage(context)
}