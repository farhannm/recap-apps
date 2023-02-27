package com.bara.recapitulation.ui.Settings.SettingsAdmin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingAdminViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is recap Fragment"
    }
    val text: LiveData<String> = _text
}