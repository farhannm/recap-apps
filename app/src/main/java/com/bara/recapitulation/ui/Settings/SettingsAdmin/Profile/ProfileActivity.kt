package com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityAdminProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun dialogLogout(){

    }

    private fun intentView() {
        binding.settingsDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnLogout.setOnClickListener{
            dialogLogout()
        }
    }
}