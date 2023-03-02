package com.bara.recapitulation.ui.Settings.SettingsAdmin.Tentang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityAdminTentangBinding

class TentangActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminTentangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminTentangBinding.inflate(layoutInflater)
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