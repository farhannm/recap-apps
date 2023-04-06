package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import java.text.SimpleDateFormat
import java.util.*

class DetailRecapAdminViewModel(val repo: AppRepository) : ViewModel() {

    fun getSelectedTotalJam(id_pk: Int? = null) = repo.getSelectedTotalJam(id_pk).asLiveData()
    fun getUser(id: Int? = null) = repo.getUserByPekerjaan(id).asLiveData()
    fun getSelectedPk(id_pk: Int? = null) = repo.getSelectedPk(id_pk).asLiveData()

    val date = System.currentTimeMillis()

    val sdf = SimpleDateFormat("MMM, dd MM yyyy h:mm a", Locale.getDefault())
    val dateString: String = sdf.format(date)

    private val _dateFormat = MutableLiveData<String>().apply {
        value = dateString
    }
    val getDate: LiveData<String> = _dateFormat
}