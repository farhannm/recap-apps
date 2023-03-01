package com.bara.recapitulation.ui.Settings.SettingsUser.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityAuthBinding
import com.bara.recapitulation.databinding.ActivityProfileBinding
import com.bara.recapitulation.ui.CustomDialog.CustomDialog
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ChangePass.ChangePasswordActivity
import com.inyongtisto.myhelper.extension.pushActivity
import kotlin.math.truncate

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
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