package com.bara.recapitulation.core.di

import android.app.Activity
import com.bara.recapitulation.ui.Auth.AuthViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.DashboardAdminViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.CreateKaryawanViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.KaryawanViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan.CreatePekerjaanUserViewModel
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.CreatePekerjaanRecapViewModel
import com.bara.recapitulation.ui.Recap.RecapAdmin.RecapAdminViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileViewModel(get()) }
    viewModel { com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile.ProfileViewModel(get()) }
    viewModel { DashboardAdminViewModel(get()) }
    viewModel { RecapAdminViewModel(get()) }
    viewModel { KaryawanViewModel(get()) }
    viewModel { CreateKaryawanViewModel(get()) }
    viewModel { CreatePekerjaanUserViewModel(get()) }
    viewModel { CreatePekerjaanRecapViewModel(get()) }
}