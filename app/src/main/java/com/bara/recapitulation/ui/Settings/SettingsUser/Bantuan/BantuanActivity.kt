package com.bara.recapitulation.ui.Settings.SettingsUser.Bantuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityUserBantuanBinding

class BantuanActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBantuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBantuanBinding.inflate(layoutInflater)
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