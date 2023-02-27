package com.bara.recapitulation.core.di

import com.bara.recapitulation.core.data.source.local.LocalDataSource
import com.bara.recapitulation.core.data.source.remote.RemoteDataSource
import com.bara.recapitulation.core.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.provideApiService }
    single { RemoteDataSource(get()) }
    single { LocalDataSource() }
}