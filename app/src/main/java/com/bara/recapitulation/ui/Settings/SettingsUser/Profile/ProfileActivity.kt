package com.bara.recapitulation.ui.Settings.SettingsUser.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityUserProfileBinding
import com.bara.recapitulation.ui.CustomDialog.CustomDialog
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ChangePass.ChangePasswordActivity

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun dialogLogout(){
        val logout = CustomDialog(this)
        logout.dialogLogout()
    }

    private fun intentView() {
        binding.btnChangePass.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        binding.settingsDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnLogout.setOnClickListener{
            dialogLogout()
        }
    }
}