package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.remote.request.RegisterRequest
import com.bara.recapitulation.ui.CustomDialog.MyDialog

class CreateKaryawanViewModel(val repo: AppRepository): ViewModel(){
    fun createUser(data: RegisterRequest) = repo.register(data).asLiveData()

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

    fun dialogSuccess(myActivity: Activity){
        val success = MyDialog(mActivity = myActivity)
        success.dialogSuccessCreatUser()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                success.dialogDismiss()
            }

        }, 2000)
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