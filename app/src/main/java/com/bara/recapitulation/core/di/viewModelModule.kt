package com.bara.recapitulation.core.di

import android.app.Activity
import com.bara.recapitulation.ui.Auth.AuthViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.DashboardAdminViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.CreateKaryawanViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.KaryawanViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan.CreatePekerjaanUserViewModel
import com.bara.recapitulation.ui.Dashboard.DashboardUser.TodayTask.TodayTaskViewModel
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.CreatePekerjaanRecapViewModel
import com.bara.recapitulation.ui.Recap.RecapAdmin.RecapAdminViewModel
import com.bara.recapitulation.ui.Recap.RecapUser.Recap.DetailRecapUserViewModel
import com.bara.recapitulation.ui.Recap.RecapUser.RecapUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileViewModel(get()) }
    viewModel { com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile.ProfileViewModel(get()) }
    viewModel { DashboardAdminViewModel(get()) }
    viewModel { DashboardUserViewModel(get()) }
    viewModel { TodayTaskViewModel(get()) }
    viewModel { RecapAdminViewModel(get()) }
    viewModel { RecapUserViewModel(get()) }
    viewModel { DetailRecapUserViewModel(get()) }
    viewModel { KaryawanViewModel(get()) }
    viewModel { CreateKaryawanViewModel(get()) }
    viewModel { CreatePekerjaanUserViewModel(get()) }
    viewModel { CreatePekerjaanRecapViewModel(get()) }
}