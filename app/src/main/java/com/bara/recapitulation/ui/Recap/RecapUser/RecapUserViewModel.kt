package com.bara.recapitulation.ui.Recap.RecapUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class RecapUserViewModel : ViewModel() {
    val date = System.currentTimeMillis()

    val sdf = SimpleDateFormat("MMMM", Locale.getDefault())
    val dateString: String = sdf.format(date)

    private val _dateFormat = MutableLiveData<String>().apply {
        value = dateString
    }
    val getDate: LiveData<String> = _dateFormat
}