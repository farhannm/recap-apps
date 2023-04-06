package com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.remote.request.DetailPkRequest
import com.bara.recapitulation.core.data.source.remote.request.UserRequest
import com.bara.recapitulation.ui.CustomDialog.MyDialog
import okhttp3.MultipartBody

class CreatePekerjaanUserViewModel(val repo: AppRepository): ViewModel(){
    fun createPekerjaanUser(data: DetailPkRequest) = repo.createDetailPk(data).asLiveData()

    fun getPekerjaanMonth() = repo.getPekerjaanMonth().asLiveData()

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

    fun dialogSuccessCreate(myActivity: Activity){
        val success = MyDialog(mActivity = myActivity)
        success.dialogSuccessCreateTask()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                success.dialogDismiss()
            }

        }, 2500)
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

    fun dialogLogout(myActivity: Activity){
        val logout = MyDialog(mActivity = myActivity)
        logout.dialogLogout()
    }

}