package com.bara.recapitulation.ui.Dashboard.DashboardUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardUserViewModel : ViewModel() {

        val date = System.currentTimeMillis()

        val sdf = SimpleDateFormat("MMM, dd MM yyyy h:mm a", Locale.getDefault())
        val dateString: String = sdf.format(date)

        private val _dateFormat = MutableLiveData<String>().apply {
            value = dateString
        }
        val getDate: LiveData<String> = _dateFormat
}