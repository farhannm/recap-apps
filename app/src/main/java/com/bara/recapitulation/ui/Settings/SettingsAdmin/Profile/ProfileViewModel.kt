package com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.remote.request.UserRequest
import com.bara.recapitulation.ui.CustomDialog.MyDialog
import okhttp3.MultipartBody

class ProfileViewModel(val repo: AppRepository): ViewModel(){

    fun dialogLogout(myActivity: Activity){
        val logout = MyDialog(mActivity = myActivity)
        logout.dialogLogout()
    }

}