package com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ChangePass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.UserRequest
import com.bara.recapitulation.databinding.ActivityChangePasswordBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileActivity
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileViewModel
import com.bara.recapitulation.util.Pref
import com.bara.recapitulation.util.getUserId
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityChangePasswordBinding
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    private fun mainButton() {
        binding.btnChangePass.setOnClickListener {
            if (binding.inputPasswordLama.isEmpty()){
                binding.inputPasswordLama.error = "Password Lama tidak boleh kosong"
                binding.inputPasswordLama.requestFocus()
            }else if (binding.inputPasswordBaru.isEmpty()){
                binding.inputPasswordBaru.error = "Password Baru tidak boleh kosong"
                binding.inputPasswordBaru.requestFocus()
            }else if (binding.inputKonfirmasiPassword.isEmpty()){
                binding.inputKonfirmasiPassword.error = "Konfirmasi Password baru anda"
                binding.inputKonfirmasiPassword.requestFocus()
            }else if (binding.inputKonfirmasiPassword.text.toString() != binding.inputPasswordBaru.text.toString()) {
                binding.inputKonfirmasiPassword.error = "Password tidak sesuai."
                binding.inputKonfirmasiPassword.requestFocus()
            }else {
                changePass()
            }
        }

        binding.profileDest.setOnClickListener {
            onSupportNavigateUp()
        }

    }

    private fun changePass() {
        val body = UserRequest(
            getUserId(),
            password = binding.inputPasswordBaru.text.toString(),
        )

        viewModel.updateUser(body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    showToast("Berhasil mengganti password")
                }
                State.FAILED -> {
                    viewModel.dialogFailed(this)
                }
                State.LOADING -> {
                    viewModel.dialogLoading(this)
                    val handler = android.os.Handler()
                    handler.postDelayed(object : Runnable {
                        override fun run() {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    }, 2500)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}