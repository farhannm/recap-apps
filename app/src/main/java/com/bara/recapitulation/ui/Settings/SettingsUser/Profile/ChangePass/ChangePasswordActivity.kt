package com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ChangePass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityChangePasswordBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileActivity
import com.inyongtisto.myhelper.extension.pushActivity

class ChangePasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun intentView() {
        binding.btnChangePass.setOnClickListener {

        }

        binding.profileDest.setOnClickListener {
            pushActivity(ProfileActivity::class.java)
            finish()
        }

    }
}