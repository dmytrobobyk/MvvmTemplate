package com.dmytro.mvvmtemplate.di

import dagger.Module
import dagger.Provides
import com.dmytro.mvvmtemplate.common.rx.RxWorkers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun workers() = RxWorkers(Schedulers.io(), AndroidSchedulers.mainThread())
}