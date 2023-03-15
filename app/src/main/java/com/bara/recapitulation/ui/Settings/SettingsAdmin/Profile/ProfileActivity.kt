package com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityAdminProfileBinding
import com.bara.recapitulation.util.Pref
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {
    private val viewModel: ProfileViewModel by viewModel()
    lateinit var binding: ActivityAdminProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
        setData()
    }

    private fun setData(){
        val user = Pref.getUser()
        if (user != null) {

            binding.apply {
                profileName.text = user.nama
                profileEmail.text = user.email
                profileRole.text = user.status

            }
        }
    }

    private fun mainButton() {
        binding.settingsDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnLogout.setOnClickListener{
            viewModel.dialogLogout(this)
        }
    }
}