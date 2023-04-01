package com.bara.recapitulation.ui.Settings.SettingsUser.Profile

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.remote.request.UserRequest
import com.bara.recapitulation.ui.CustomDialog.MyDialog
import okhttp3.MultipartBody

class ProfileViewModel(val repo: AppRepository): ViewModel(){
    fun updateUser(data: UserRequest) = repo.updateUser(data).asLiveData()
    fun getCurrentUser() = repo.getSingleUser().asLiveData()
    fun uploadUser(fileImage: MultipartBody.Part? = null) = repo.uploadUser(fileImage).asLiveData()

    fun dialogLoading(myActivity: Activity){
        val loading = MyDialog(mActivity = myActivity)
        loading.dialogLoading()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                loading.dialogDismiss()
            }

        }, 2000)
    }

    fun dialogSuccess(myActivity: Activity){
        val success = MyDialog(mActivity = myActivity)
        success.dialogSuccessProfile()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                success.dialogDismiss()
            }

        }, 3500)
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