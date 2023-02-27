package com.bara.recapitulation.core.di

import com.bara.recapitulation.ui.Auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
}