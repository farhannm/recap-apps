package com.bara.recapitulation.ui.Recap.RecapUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.local.DummyData
import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import java.text.SimpleDateFormat
import java.util.*

class RecapUserViewModel(private val repo: AppRepository) : ViewModel() {
    fun getPekerjaan() = repo.getPekerjaan().asLiveData()

    val date = System.currentTimeMillis()

    val sdf = SimpleDateFormat("MMMM", Locale.getDefault())
    val dateString: String = sdf.format(date)

    private val _dateFormat = MutableLiveData<String>().apply {
        value = dateString
    }
    val getDate: LiveData<String> = _dateFormat

    val listDetailPekerjaan : LiveData<List<DetailPekerjaan>> = MutableLiveData<List<DetailPekerjaan>>().apply {
        value = DummyData.listDetailPekerjaan
    }

    val listPekerjaan : LiveData<List<Pekerjaan>> = MutableLiveData<List<Pekerjaan>>().apply {
        value = DummyData.listPekerjaan
    }
}