package com.bara.recapitulation.core.di

import com.bara.recapitulation.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}