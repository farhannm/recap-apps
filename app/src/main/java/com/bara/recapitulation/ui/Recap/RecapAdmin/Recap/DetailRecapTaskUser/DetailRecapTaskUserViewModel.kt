package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.DetailRecapTaskUser

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.remote.request.DetailPkRequest
import com.bara.recapitulation.ui.CustomDialog.MyDialog
import java.text.SimpleDateFormat
import java.util.*

class DetailRecapTaskUserViewModel(val repo: AppRepository) : ViewModel() {

    fun getTaskById(id: Int? = null, id_pk: Int? = null) = repo.getTaskById(id, id_pk).asLiveData()
    fun getTotalJamByUser(id: Int? = null, id_pk: Int? = null) = repo.getTotalJamByUser(id, id_pk).asLiveData()
    fun getSelectedPk(id_pk: Int? = null) = repo.getSelectedPk(id_pk).asLiveData()

    fun dialogLoading(myActivity: Activity){
        val loading = MyDialog(mActivity = myActivity)
        loading.dialogLoading()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                loading.dialogDismiss()
            }

        }, 1000)
    }


    fun dialogFailed(myActivity: Activity){
        val failed = MyDialog(mActivity = myActivity)
        failed.dialogFailedGeneric()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                failed.dialogDismiss()
            }

        }, 3500)
    }

    fun dialogSuccessEdit(myActivity: Activity){
        val edit = MyDialog(mActivity = myActivity)
        edit.dialogSuccessEditTask()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                edit.dialogDismiss()
            }

        }, 2500)
    }

    fun dialogSuccessDelete(myActivity: Activity){
        val delete = MyDialog(mActivity = myActivity)
        delete.dialogSuccessDeleteTask()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                delete.dialogDismiss()
            }

        }, 2500)
    }

    val date = System.currentTimeMillis()

    val sdf = SimpleDateFormat("MMM, dd MM yyyy h:mm a", Locale.getDefault())
    val dateString: String = sdf.format(date)

    private val _dateFormat = MutableLiveData<String>().apply {
        value = dateString
    }
    val getDate: LiveData<String> = _dateFormat
}