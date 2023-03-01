package com.bara.recapitulation.ui.Settings.SettingsUser.Bantuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityBantuanBinding
import com.bara.recapitulation.databinding.ActivityChangePasswordBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileActivity
import com.inyongtisto.myhelper.extension.pushActivity

class BantuanActivity : AppCompatActivity() {
    lateinit var binding: ActivityBantuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBantuanBinding.inflate(layoutInflater)
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