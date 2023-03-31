package com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.AdminActivity
import com.bara.recapitulation.UserActivity
import com.bara.recapitulation.databinding.ActivityAdminProfileBinding
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.pushActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        mainButton()
    }

    private fun getData(){
//        val user = Pref.getUser()
//        binding.apply {
//            profileName.text = user?.nama
//            profileEmail.text = user?.email
//        }
    }

    private fun mainButton() {
        binding.settingsDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}