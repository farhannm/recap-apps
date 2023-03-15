package com.bara.recapitulation.ui.Auth

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.ui.CustomDialog.MyDialog

class AuthViewModel(val repo: AppRepository): ViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "Halo aku Farhan."
    }
    val text: LiveData<String> = _text

    fun ubahData(){
        _text.postValue("Halo aku Paan.")
    }

    fun login(data: AuthRequest) = repo.login(data).asLiveData()
//    fun register(data: RegisterRequest) = repo.register(data).asLiveData()

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
        success.dialogSuccessGeneric()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                success.dialogDismiss()
            }

        }, 800)
    }

    fun dialogFailed(myActivity: Activity){
        val failed = MyDialog(mActivity = myActivity)
        failed.dialogFailedAuth()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                failed.dialogDismiss()
            }

        }, 1800)
    }
}