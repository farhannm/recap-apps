package com.bara.recapitulation.ui.Settings.SettingsUser.Tentang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityUserTentangBinding

class TentangActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserTentangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserTentangBinding.inflate(layoutInflater)
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