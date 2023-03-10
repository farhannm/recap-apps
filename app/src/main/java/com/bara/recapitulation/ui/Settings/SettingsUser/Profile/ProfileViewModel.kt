package com.bara.recapitulation.ui.Settings.SettingsUser.Profile

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.remote.request.UpdateUserRequest
import com.bara.recapitulation.ui.CustomDialog.CustomDialogActivity
import okhttp3.MultipartBody

class ProfileViewModel(val repo: AppRepository): ViewModel(){
    fun updateUser(data: UpdateUserRequest) = repo.updateUser(data).asLiveData()
    fun uploadUser(token: String? = null, id: Int? = null, fileImage: MultipartBody.Part? = null) = repo.uploadUser(token, id, fileImage).asLiveData()

    fun dialogLoading(myActivity: Activity){
        val loading = CustomDialogActivity(mActivity = myActivity)
        loading.dialogLoading()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                loading.dialogDismiss()
            }

        }, 1000)
    }

    fun dialogSuccess(myActivity: Activity){
        val success = CustomDialogActivity(mActivity = myActivity)
        success.dialogSuccess()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                success.dialogDismiss()
            }

        }, 3000)
    }

    fun dialogFailed(myActivity: Activity){
        val failed = CustomDialogActivity(mActivity = myActivity)
        failed.dialogFailed()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                failed.dialogDismiss()
            }

        }, 1000)
    }

    fun dialogLogout(myActivity: Activity){
        val logout = CustomDialogActivity(mActivity = myActivity)
        logout.dialogLogout()
    }

}