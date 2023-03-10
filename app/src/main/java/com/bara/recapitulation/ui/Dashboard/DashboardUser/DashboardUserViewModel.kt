package com.bara.recapitulation.ui.Dashboard.DashboardUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bara.recapitulation.ui.CustomDialog.CustomDialogActivity
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardUserViewModel : ViewModel() {

        val date = System.currentTimeMillis()

        val sdf = SimpleDateFormat("dd MMMM, yyyy h:mm a", Locale.getDefault())
        val dateString: String = sdf.format(date)

        private val _dateFormat = MutableLiveData<String>().apply {
            value = dateString
        }
        val getDate: LiveData<String> = _dateFormat

//        fun dialogSuccess(myFragment: DashboardUserFragment){
//                val success = CustomDialogActivity(myFragment.requireActivity())
//                success.dialogSuccess()
//                val handler = android.os.Handler()
//                handler.postDelayed(object : Runnable {
//                        override fun run() {
//                                success.dialogDismiss()
//                        }
//
//                }, 2000)
//        }
}