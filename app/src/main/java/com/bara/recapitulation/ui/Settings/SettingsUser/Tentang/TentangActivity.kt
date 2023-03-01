package com.bara.recapitulation.ui.Settings.SettingsUser.Tentang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityChangePasswordBinding
import com.bara.recapitulation.databinding.ActivityTentangBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileActivity
import com.inyongtisto.myhelper.extension.pushActivity

class TentangActivity : AppCompatActivity() {
    lateinit var binding: ActivityTentangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTentangBinding.inflate(layoutInflater)
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