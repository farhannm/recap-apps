package com.bara.recapitulation.ui.Recap.RecapAdmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bara.recapitulation.core.data.source.local.DummyData
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.core.data.source.model.User

class RecapAdminViewModel : ViewModel() {

    val listPekerjaan : LiveData<List<Pekerjaan>> = MutableLiveData<List<Pekerjaan>>().apply {
        value = DummyData.listPekerjaan
    }
}