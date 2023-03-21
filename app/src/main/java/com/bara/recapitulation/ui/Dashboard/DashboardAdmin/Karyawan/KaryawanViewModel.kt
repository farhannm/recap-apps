package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bara.recapitulation.core.data.source.local.DummyData
import com.bara.recapitulation.core.data.source.model.User

class KaryawanViewModel : ViewModel() {

    val listUser : LiveData<List<User>> = MutableLiveData<List<User>>().apply {
        value = DummyData.listUser
    }
}