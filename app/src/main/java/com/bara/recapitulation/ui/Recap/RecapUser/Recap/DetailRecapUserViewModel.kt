package com.bara.recapitulation.ui.Recap.RecapUser.Recap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import java.text.SimpleDateFormat
import java.util.*

class DetailRecapUserViewModel(val repo: AppRepository) : ViewModel() {

    fun getUserTaskByMonth(id_pk: Int? = null) = repo.getUserTaskByMonth(id_pk).asLiveData()
    fun getSelectedTotalJam(id_pk: Int? = null) = repo.getSelectedTotalJam(id_pk).asLiveData()
    fun getSelectedPk(id_pk: Int? = null) = repo.getSelectedPk(id_pk).asLiveData()

    val date = System.currentTimeMillis()

    val sdf = SimpleDateFormat("MMM, dd MM yyyy h:mm a", Locale.getDefault())
    val dateString: String = sdf.format(date)

    private val _dateFormat = MutableLiveData<String>().apply {
        value = dateString
    }
    val getDate: LiveData<String> = _dateFormat
}