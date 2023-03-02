package com.bara.recapitulation.ui.Settings.SettingsAdmin.Bantuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityAdminBantuanBinding

class BantuanActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminBantuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBantuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun intentView() {
        binding.settingsDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
    }
}