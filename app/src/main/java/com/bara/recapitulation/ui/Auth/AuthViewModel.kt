package com.bara.recapitulation.ui.Auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bara.recapitulation.core.data.repository.AppRepository
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest

class AuthViewModel(val repo: AppRepository): ViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "Halo aku Farhan."
    }
    val text: LiveData<String> = _text

    fun ubahData(){
        _text.postValue("Halo aku Paan.")
    }

    fun login(data: AuthRequest) = repo.login(data).asLiveData()
}