package com.bara.recapitulation.core.di

import android.app.Activity
import com.bara.recapitulation.ui.Auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileViewModel(get()) }
    viewModel { com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile.ProfileViewModel(get()) }
}