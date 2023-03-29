package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.local.DummyData
import com.bara.recapitulation.core.data.source.model.User

class KaryawanViewModel(private val repo: AppRepository) : ViewModel() {
    fun getUser() = repo.getUser().asLiveData()

    fun searchUser(nama: String) = repo.searchUser(nama).asLiveData()

    val listUser : LiveData<List<User>> = MutableLiveData<List<User>>().apply {
        value = DummyData.listUser
    }


}