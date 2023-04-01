package com.bara.recapitulation.ui.Dashboard.DashboardUser.TodayTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.local.DummyData
import com.bara.recapitulation.core.data.source.model.User

class TodayTaskViewModel(private val repo: AppRepository) : ViewModel() {
    fun getUserTodayTask() = repo.getUserTodayTask().asLiveData()

    val listUser : LiveData<List<User>> = MutableLiveData<List<User>>().apply {
        value = DummyData.listUser
    }


}